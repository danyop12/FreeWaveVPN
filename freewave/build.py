from __future__ import annotations

import base64
import concurrent.futures
import ipaddress
import re
import socket
import time
import urllib.request
from pathlib import Path
from urllib.parse import unquote, urlsplit

ROOT = Path(__file__).resolve().parent.parent
SOURCES_FILE = ROOT / "sources.txt"
OUTPUT_DIR = ROOT / "public"

MAX_SOURCE_BYTES = 2 * 1024 * 1024
MAX_NODES = 250
MAX_WORKERS = 40
FETCH_TIMEOUT = 15
TCP_TIMEOUT = 2.5
USER_AGENT = "FreeWaveVPN-Collector/0.2.0"

VLESS_PATTERN = re.compile(r"vless://[^\s\"'<>]+", re.IGNORECASE)
EU_HINTS = (
    "europe", "germany", "deutschland", "frankfurt", "netherlands",
    "amsterdam", "poland", "warsaw", "france", "paris", "finland",
    "sweden", "norway", "italy", "milan", "spain", "london", "belgium",
    "switzerland", "austria", "czech", "romania", "bulgaria",
)


def log(message: str) -> None:
    print(f"[FreeWave] {message}", flush=True)


def read_sources() -> list[str]:
    if not SOURCES_FILE.is_file():
        raise RuntimeError("sources.txt was not found")

    sources: list[str] = []
    for raw_line in SOURCES_FILE.read_text(encoding="utf-8").splitlines():
        line = raw_line.strip()
        if not line or line.startswith("#"):
            continue
        parsed = urlsplit(line)
        if parsed.scheme.lower() != "https" or not parsed.hostname:
            log(f"Skipping invalid or non-HTTPS source: {line[:100]}")
            continue
        sources.append(line)

    return list(dict.fromkeys(sources))


def fetch_source(url: str) -> str:
    request = urllib.request.Request(
        url,
        headers={"User-Agent": USER_AGENT},
    )
    with urllib.request.urlopen(request, timeout=FETCH_TIMEOUT) as response:
        if urlsplit(response.geturl()).scheme.lower() != "https":
            raise ValueError("source redirected away from HTTPS")
        data = response.read(MAX_SOURCE_BYTES + 1)

    if len(data) > MAX_SOURCE_BYTES:
        raise ValueError(f"source exceeds {MAX_SOURCE_BYTES} byte limit")
    return data.decode("utf-8", errors="replace")


def decode_subscription(body: str) -> str:
    text = body.strip().lstrip("\ufeff")
    if "vless://" in text.lower():
        return text

    compact = re.sub(r"\s+", "", text)
    if not compact or not re.fullmatch(r"[A-Za-z0-9+/=_-]+", compact):
        return text

    padded = compact + ("=" * (-len(compact) % 4))
    for decoder in (base64.b64decode, base64.urlsafe_b64decode):
        try:
            decoded = decoder(padded).decode("utf-8", errors="replace")
            if "vless://" in decoded.lower():
                return decoded
        except (ValueError, UnicodeError):
            continue

    return text


def extract_links(text: str) -> list[str]:
    return [
        match.rstrip("),;]")
        for match in VLESS_PATTERN.findall(text)
    ]


def parse_endpoint(uri: str) -> tuple[str, int]:
    parsed = urlsplit(uri)
    if parsed.scheme.lower() != "vless" or not parsed.hostname:
        raise ValueError("invalid VLESS URI")

    try:
        port = parsed.port
    except ValueError as exc:
        raise ValueError("invalid port") from exc

    if port is None or not 1 <= port <= 65535:
        raise ValueError("port out of range")

    return parsed.hostname.strip("[]"), port


def is_global_ip(value: str) -> bool:
    try:
        return ipaddress.ip_address(value).is_global
    except ValueError:
        return False


def resolve_public_addresses(host: str, port: int) -> list[str]:
    try:
        literal = ipaddress.ip_address(host)
        return [str(literal)] if literal.is_global else []
    except ValueError:
        pass

    records = socket.getaddrinfo(
        host,
        port,
        type=socket.SOCK_STREAM,
    )
    addresses = sorted({record[4][0] for record in records})

    # Fail closed if DNS returns any private, loopback, link-local,
    # multicast, reserved, or otherwise non-global address.
    if not addresses or any(not is_global_ip(address) for address in addresses):
        return []

    return addresses


def check_tcp(uri: str) -> tuple[str, bool]:
    try:
        host, port = parse_endpoint(uri)
        addresses = resolve_public_addresses(host, port)
        if not addresses:
            return uri, False

        # Connect to the resolved IP itself, not the hostname, so the
        # checked address is the same address that passed the IP filter.
        for address in addresses:
            is_ipv6 = ":" in address
            family = socket.AF_INET6 if is_ipv6 else socket.AF_INET
            sockaddr = (address, port, 0, 0) if is_ipv6 else (address, port)

            sock = socket.socket(family, socket.SOCK_STREAM)
            sock.settimeout(TCP_TIMEOUT)
            try:
                sock.connect(sockaddr)
                return uri, True
            except OSError:
                continue
            finally:
                sock.close()

        return uri, False
    except (ValueError, OSError):
        return uri, False


def get_remark(uri: str) -> str:
    return unquote(urlsplit(uri).fragment or "").replace("+", " ").strip()


def looks_european_by_name(uri: str) -> bool:
    name = get_remark(uri).lower()
    return any(hint in name for hint in EU_HINTS)


def atomic_write(path: Path, content: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = path.with_suffix(path.suffix + ".tmp")
    temporary.write_text(content, encoding="utf-8", newline="\n")
    temporary.replace(path)


def write_outputs(
    reachable: list[str],
    checked_count: int,
    source_errors: list[str],
) -> None:
    if not reachable:
        raise RuntimeError("No TCP-reachable nodes; previous validated feeds are preserved.")

    plain_text = "\n".join(reachable) + "\n"
    atomic_write(OUTPUT_DIR / "freewave-tcp.txt", plain_text)

    timestamp = time.strftime("%Y-%m-%d %H:%M:%S UTC", time.gmtime())
    status_lines = [
        "# FreeWave VPN status",
        "",
        f"- Preliminary scan: {timestamp}",
        f"- TCP-reachable candidates: {len(reachable)}",
        f"- Unique candidates checked: {checked_count}",
        f"- Candidates not reachable by TCP: {checked_count - len(reachable)}",
        "",
        "These are preliminary candidates only. The Xray stage must verify an",
        "actual VLESS connection and HTTPS request before publishing them as",
        "working subscription nodes.",
        "",
    ]
    if source_errors:
        status_lines.extend(["## Source errors", ""])
        status_lines.extend(f"- {error}" for error in source_errors[:20])
        status_lines.append("")
    atomic_write(OUTPUT_DIR / "STATUS.md", "\n".join(status_lines))


def main() -> int:
    sources = read_sources()
    if not sources:
        raise RuntimeError("No valid HTTPS sources are configured.")

    all_links: list[str] = []
    source_errors: list[str] = []

    for source in sources:
        try:
            body = decode_subscription(fetch_source(source))
            links = extract_links(body)
            all_links.extend(links)
            log(f"Fetched source with {len(links)} VLESS link(s).")
        except Exception as exc:
            source_errors.append(f"{source}: {str(exc)[:160]}")
            log(f"Source failed: {source}: {exc}")

    candidates: list[str] = []
    for uri in dict.fromkeys(all_links):
        try:
            parse_endpoint(uri)
            candidates.append(uri)
        except ValueError:
            continue

    candidates = candidates[:MAX_NODES]
    log(f"Checking {len(candidates)} unique valid candidate(s).")

    with concurrent.futures.ThreadPoolExecutor(max_workers=MAX_WORKERS) as executor:
        results = list(executor.map(check_tcp, candidates))

    reachable_set = {uri for uri, is_reachable in results if is_reachable}
    reachable = [uri for uri in candidates if uri in reachable_set]

    write_outputs(reachable, len(candidates), source_errors)
    log(f"Published {len(reachable)} TCP-reachable configuration(s).")
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except Exception as exc:
        log(f"ERROR: {exc}")
        raise SystemExit(1)
