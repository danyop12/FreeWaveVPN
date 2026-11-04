from __future__ import annotations

import base64
import concurrent.futures
import ipaddress
import json
import os
import shutil
import socket
import subprocess
import tempfile
import time
from pathlib import Path
from urllib.parse import parse_qs, unquote, urlsplit

ROOT = Path(__file__).resolve().parent.parent
PUBLIC = ROOT / "public"
SOURCE = PUBLIC / "freewave-tcp.txt"
XRAY = os.environ.get("XRAY_PATH", "xray")
CURL = shutil.which("curl")
MAX_TESTS = int(os.environ.get("FREEWAVE_MAX_XRAY_TESTS", "160"))
WORKERS = 8
LATENCY_URLS = (
    "https://www.gstatic.com/generate_204",
    "https://www.google.com/generate_204",
    "https://cp.cloudflare.com/generate_204",
)
DOWNLOAD_URL = "https://speed.cloudflare.com/__down?bytes=262144"
KNOWN_KEYS = {
    "security", "encryption", "host", "type", "sni", "servername", "fp",
    "pbk", "publickey", "sid", "shortid", "spx", "spiderx", "flow",
    "servicename", "service_name", "authority", "alpn", "mode",
}


def log(message: str) -> None:
    print(f"[FreeWave rank] {message}", flush=True)


def parse_link(uri: str):
    parsed = urlsplit(uri)
    values = parse_qs(parsed.query, keep_blank_values=True)
    opts = {key.lower(): value[-1] for key, value in values.items() if value}
    path = opts.get("path", "")
    if ";" in path:
        pieces = path.split(";")
        opts["path"] = pieces[0]
        for part in pieces[1:]:
            key, sep, value = part.partition("=")
            key = key.lower().strip()
            if sep and key in KNOWN_KEYS:
                opts.setdefault(key, value)
    return parsed, opts


def get(opts: dict, *keys: str, default: str = "") -> str:
    for key in keys:
        value = opts.get(key.lower(), "")
        if value:
            return value
    return default


def public_addresses(host: str, port: int) -> list[str]:
    try:
        ip = ipaddress.ip_address(host)
        return [str(ip)] if ip.is_global else []
    except ValueError:
        pass
    answers = socket.getaddrinfo(host, port, type=socket.SOCK_STREAM)
    addresses = sorted({item[4][0] for item in answers})
    if not addresses or any(not ipaddress.ip_address(x).is_global for x in addresses):
        return []
    return addresses


def make_config(uri: str, target_ip: str, socks_port: int) -> dict:
    parsed, q = parse_link(uri)
    if parsed.scheme.lower() != "vless" or not parsed.username or not parsed.hostname:
        raise ValueError("Invalid VLESS URI")
    port = parsed.port
    if not port or not 1 <= port <= 65535:
        raise ValueError("Invalid port")

    security = get(q, "security", default="none").lower()
    transport = get(q, "type", default="tcp").lower()
    methods = {
        "tcp": "raw", "raw": "raw", "ws": "websocket",
        "websocket": "websocket", "grpc": "grpc",
        "httpupgrade": "httpupgrade", "xhttp": "xhttp",
        "splithttp": "xhttp", "http": "xhttp", "kcp": "mkcp",
    }
    method = methods.get(transport)
    if method is None:
        raise ValueError(f"Unsupported transport: {transport}")
    if security not in {"none", "tls", "reality"}:
        raise ValueError(f"Unsupported security: {security}")

    path = get(q, "path", default="/") or "/"
    early_data = get(q, "ed")
    if early_data and "ed=" not in path:
        path += ("&" if "?" in path else "?") + "ed=" + early_data
    # If a link omits the optional transport Host, preserve its original
    # URI hostname even though the network connection is pinned to a checked IP.
    host_header = get(q, "host", default=parsed.hostname)
    server_name = get(q, "sni", "servername", default=parsed.hostname)
    fingerprint = get(q, "fp", default="chrome")
    flow = get(q, "flow")
    encryption = get(q, "encryption", default="none")
    user_id = unquote(parsed.username)

    if security == "none" and encryption.lower() in {"", "none"}:
        raise ValueError("Public VLESS without TLS/REALITY or VLESS encryption is rejected")

    stream = {"method": method, "security": security}
    if method == "raw":
        stream["rawSettings"] = {"header": {"type": "none"}}
    elif method == "websocket":
        stream["wsSettings"] = {
            "path": path,
            **({"host": host_header} if host_header else {}),
        }
    elif method == "httpupgrade":
        stream["httpupgradeSettings"] = {
            "path": path,
            **({"host": host_header} if host_header else {}),
        }
    elif method == "grpc":
        grpc = {
            "serviceName": get(q, "servicename", "service_name", "path"),
            "multiMode": get(q, "mode").lower() in {"multi", "multimode"},
        }
        authority = get(q, "authority", "host", default=parsed.hostname)
        if authority:
            grpc["authority"] = authority
        stream["grpcSettings"] = grpc
    elif method == "xhttp":
        xhttp = {"path": path}
        mode = get(q, "mode")
        if mode:
            xhttp["mode"] = mode
        if host_header:
            xhttp["host"] = host_header
        stream["xhttpSettings"] = xhttp
    elif method == "mkcp":
        stream["kcpSettings"] = {"header": {"type": "none"}}

    if security == "tls":
        tls = {"serverName": server_name, "fingerprint": fingerprint}
        alpn = get(q, "alpn")
        if alpn:
            tls["alpn"] = [x.strip() for x in alpn.split(",") if x.strip()]
        elif method in {"websocket", "httpupgrade"}:
            tls["alpn"] = ["http/1.1"]
        elif method == "grpc":
            tls["alpn"] = ["h2"]
        stream["tlsSettings"] = tls
    elif security == "reality":
        public_key = get(q, "pbk", "publickey")
        if not public_key:
            raise ValueError("REALITY link has no public key")
        if method not in {"raw", "xhttp", "grpc"}:
            raise ValueError("REALITY is not supported for this transport")
        stream["realitySettings"] = {
            "serverName": server_name,
            "fingerprint": fingerprint,
            "password": public_key,
            "shortId": get(q, "sid", "shortid"),
            "spiderX": get(q, "spx", "spiderx"),
        }

    settings = {
        "address": target_ip,
        "port": port,
        "id": user_id,
        "encryption": encryption,
    }
    if flow:
        settings["flow"] = flow

    return {
        "log": {"loglevel": "warning"},
        "inbounds": [{
            "listen": "127.0.0.1",
            "port": socks_port,
            "protocol": "socks",
            "settings": {"auth": "noauth", "udp": False},
            "tag": "local-socks",
        }],
        "outbounds": [{
            "tag": "vless-node",
            "protocol": "vless",
            "settings": settings,
            "streamSettings": stream,
        }],
        "routing": {
            "domainStrategy": "AsIs",
            "rules": [{
                "type": "field",
                "inboundTag": ["local-socks"],
                "outboundTag": "vless-node",
            }],
        },
    }


def free_port() -> int:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.bind(("127.0.0.1", 0))
        return int(sock.getsockname()[1])


def curl_request(port: int, url: str, seconds: int, fmt: str):
    command = [
        CURL, "--silent", "--show-error", "--connect-timeout", "5",
        "--max-time", str(seconds), "--proxy", f"socks5h://127.0.0.1:{port}",
        "--output", os.devnull, "--write-out", fmt, url,
    ]
    result = subprocess.run(command, capture_output=True, text=True, timeout=seconds + 2)
    chunks = result.stdout.strip().split("\t")
    try:
        return int(float(chunks[0])), [float(x) for x in chunks[1:]], result.stderr.strip()[:160]
    except (ValueError, IndexError):
        return 0, [], result.stderr.strip()[:160]


def test_node(uri: str) -> dict:
    parsed, _ = parse_link(uri)
    host = parsed.hostname or ""
    port = parsed.port
    if not host or not port:
        return {"uri": uri, "ok": False, "error": "Missing host or port"}
    try:
        addresses = public_addresses(host, port)
    except Exception as exc:
        return {"uri": uri, "ok": False, "host": host, "port": port, "error": str(exc)[:200]}
    if not addresses:
        return {"uri": uri, "ok": False, "host": host, "port": port, "error": "No exclusively public DNS result"}

    last_error = "No resolved address accepted the test"
    for address in addresses[:3]:
        local_port = free_port()
        try:
            config = make_config(uri, address, local_port)
        except Exception as exc:
            return {"uri": uri, "ok": False, "host": host, "port": port, "error": str(exc)[:200]}

        with tempfile.TemporaryDirectory(prefix="freewave-probe-") as tmp:
            config_file = Path(tmp) / "xray.json"
            config_file.write_text(json.dumps(config), encoding="utf-8")
            validation = subprocess.run(
                [XRAY, "run", "-test", "-c", str(config_file)],
                capture_output=True, text=True, timeout=8, check=False,
            )
            if validation.returncode != 0:
                detail = (validation.stderr or validation.stdout).strip().splitlines()
                reason = " | ".join(detail[-3:])[-700:] if detail else "unknown config error"
                return {"uri": uri, "ok": False, "host": host, "port": port, "error": f"Xray config rejected: {reason}"}

            log_path = Path(tmp) / "xray.log"
            log_handle = log_path.open("w", encoding="utf-8")
            try:
                process = subprocess.Popen(
                    [XRAY, "run", "-c", str(config_file)],
                    stdout=log_handle,
                    stderr=subprocess.STDOUT,
                )
            except OSError as exc:
                log_handle.close()
                return {"uri": uri, "ok": False, "error": f"Xray launch failed: {exc}"}

            try:
                deadline = time.monotonic() + 8
                ready = False
                while time.monotonic() < deadline:
                    if process.poll() is not None:
                        break
                    try:
                        with socket.create_connection(("127.0.0.1", local_port), timeout=0.2):
                            ready = True
                            break
                    except OSError:
                        time.sleep(0.1)
                if not ready:
                    log_handle.flush()
                    detail_lines = [
                        line.strip()
                        for line in log_path.read_text(encoding="utf-8", errors="replace").splitlines()
                        if line.strip()
                    ]
                    reason = " | ".join(detail_lines[-3:])[-500:] if detail_lines else "no startup log"
                    last_error = f"Xray did not start: {reason}"
                    continue

                latency_ms = None
                for endpoint in LATENCY_URLS:
                    try:
                        code, values, curl_error = curl_request(
                            local_port, endpoint, 8, "%{http_code}\t%{time_total}"
                        )
                        if code == 204 and values and values[0] > 0:
                            latency_ms = values[0] * 1000
                            break
                        last_error = f"HTTPS probe returned HTTP {code}: {curl_error or 'no response'}"
                    except (subprocess.TimeoutExpired, OSError):
                        pass
                if latency_ms is None:
                    continue

                speed_mbps = None
                try:
                    code, values, _curl_error = curl_request(
                        local_port,
                        DOWNLOAD_URL,
                        12,
                        "%{http_code}\t%{time_starttransfer}\t%{speed_download}\t%{size_download}",
                    )
                    if code == 200 and len(values) >= 3 and values[2] > 0:
                        speed_mbps = values[1] * 8 / 1_000_000
                except (subprocess.TimeoutExpired, OSError):
                    pass

                return {
                    "uri": uri, "ok": True, "host": host, "port": port,
                    "latency_ms": round(latency_ms, 2),
                    "speed_mbps": round(speed_mbps, 3) if speed_mbps is not None else None,
                    "speed_test_ok": speed_mbps is not None,
                }
            finally:
                if process.poll() is None:
                    process.terminate()
                try:
                    process.wait(timeout=2)
                except subprocess.TimeoutExpired:
                    process.kill()
                    process.wait(timeout=2)
                log_handle.close()

    return {"uri": uri, "ok": False, "host": host, "port": port, "error": last_error}


def main() -> int:
    if not (Path(XRAY).is_file() or shutil.which(XRAY)):
        raise RuntimeError("Xray binary missing; configure XRAY_PATH")
    if not CURL:
        raise RuntimeError("curl is required")
    if not SOURCE.is_file():
        raise RuntimeError("freewave-tcp.txt missing; run the collector first")

    candidates = list(dict.fromkeys(
        line.strip() for line in SOURCE.read_text(encoding="utf-8").splitlines()
        if line.strip().lower().startswith("vless://")
    ))
    selected = candidates[:MAX_TESTS]
    log(f"Testing {len(selected)} of {len(candidates)} TCP candidates with Xray.")

    results = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=WORKERS) as pool:
        futures = [pool.submit(test_node, uri) for uri in selected]
        for future in concurrent.futures.as_completed(futures):
            item = future.result()
            results.append(item)
            if item.get("ok"):
                speed = f"{item['speed_mbps']:.2f} Mbps" if item.get("speed_mbps") is not None else "speed unavailable"
                log(f"VLESS OK {item['host']}:{item['port']} — {item['latency_ms']:.0f} ms, {speed}")
            else:
                log(f"VLESS failed: {item.get('error', 'unknown error')}")

    passed = [item for item in results if item.get("ok")]
    speeds = [item["speed_mbps"] for item in passed if item.get("speed_mbps") is not None]
    min_latency = min((item["latency_ms"] for item in passed), default=1)
    max_speed = max(speeds, default=0)
    for item in passed:
        latency_score = min_latency / max(item["latency_ms"], 1)
        speed = item.get("speed_mbps") or 0
        speed_score = speed / max_speed if max_speed else 0
        item["score"] = round(0.35 * latency_score + 0.65 * speed_score, 4)

    passed.sort(
        key=lambda x: (x.get("score", 0), x.get("speed_mbps") or 0, -x["latency_ms"]),
        reverse=True,
    )

    if passed:
        full = "\n".join(item["uri"] for item in passed) + "\n"
        (PUBLIC / "freewave-vless.txt").write_text(full, encoding="utf-8")
        (PUBLIC / "freewave-vless-base64.txt").write_text(
            base64.b64encode(full.encode()).decode() + "\n", encoding="utf-8"
        )
        (PUBLIC / "freewave-fast.txt").write_text(
            "\n".join(item["uri"] for item in passed[:25]) + "\n",
            encoding="utf-8",
        )
        eu_hints = (
            "europe", "germany", "frankfurt", "netherlands", "poland",
            "france", "finland", "sweden", "norway", "italy", "spain",
            "london", "belgium", "switzerland", "austria", "czech",
            "romania", "bulgaria",
        )
        eu = [
            item["uri"] for item in passed
            if any(hint in unquote(urlsplit(item["uri"]).fragment).lower() for hint in eu_hints)
        ]
        (PUBLIC / "freewave-eu.txt").write_text(
            "\n".join(eu) + ("\n" if eu else ""), encoding="utf-8"
        )

    else:
        # Do not leave earlier TCP-only configs published as verified subscriptions.
        for filename in (
            "freewave-vless.txt",
            "freewave-vless-base64.txt",
            "freewave-fast.txt",
            "freewave-eu.txt",
        ):
            (PUBLIC / filename).write_text("", encoding="utf-8")

    timestamp = time.strftime("%Y-%m-%d %H:%M:%S UTC", time.gmtime())
    metrics = {
        "generated_at_utc": timestamp,
        "measurement_location": "GitHub Actions runner; not device-specific",
        "candidate_count": len(candidates),
        "tested_count": len(results),
        "validated_count": len(passed),
        "failed_count": len(results) - len(passed),
        "not_tested_count": max(0, len(candidates) - len(results)),
        "results": [
            {key: item.get(key) for key in (
                "host", "port", "ok", "latency_ms", "speed_mbps", "score",
                "speed_test_ok", "error"
            )}
            for item in passed + [x for x in results if not x.get("ok")]
        ],
    }
    (PUBLIC / "metrics.json").write_text(
        json.dumps(metrics, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )

    lines = [
        "# FreeWave VPN status", "",
        f"- Last run: {timestamp}",
        f"- TCP candidates: {len(candidates)}",
        f"- Tested through Xray: {len(results)}",
        f"- Passed real HTTPS request through VLESS: {len(passed)}",
        f"- Failed VLESS test: {len(results) - len(passed)}",
        f"- Not tested due to limit: {max(0, len(candidates) - len(results))}",
        f"- Successful 256 KiB download tests: {sum(1 for x in passed if x.get('speed_test_ok'))}",
        "", "## Top nodes from this GitHub Actions run", "",
        "| # | Server | Latency | Download test | Score |",
        "|---:|---|---:|---:|---:|",
    ]
    for index, item in enumerate(passed[:10], 1):
        speed = f"{item['speed_mbps']:.2f} Mbps" if item.get("speed_mbps") is not None else "not measured"
        lines.append(
            f"| {index} | {item['host']}:{item['port']} | "
            f"{item['latency_ms']:.0f} ms | {speed} | {item.get('score', 0):.3f} |"
        )
    lines += [
        "", "## Subscription files", "",
        "- freewave-vless.txt — only nodes passing a real HTTPS request through Xray",
        "- freewave-fast.txt — top 25 by relative score",
        "- freewave-vless-base64.txt — Base64 version of validated nodes",
        "- freewave-tcp.txt — preliminary TCP-only candidates",
        "- metrics.json — detailed test results",
        "",
        "> Measurements are from GitHub Actions, not from your phone. Your ISP,",
        "> country, route, time of day, and client settings can change the result.",
        "> The small download test is a snapshot, not a sustained-speed or unlimited-traffic guarantee.",
        "> A successful HTTPS request verifies one VLESS route to the probe target at test time;",
        "> it does not prove all sites/apps, UDP support, privacy, or future uptime.",
        "> Public nodes are third-party infrastructure and may log metadata or disappear.",
        "",
    ]
    (PUBLIC / "STATUS.md").write_text("\n".join(lines), encoding="utf-8")
    log(f"Validated {len(passed)} node(s).")
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except Exception as exc:
        log(f"ERROR: {exc}")
        raise SystemExit(1)
