from __future__ import annotations

import base64
import concurrent.futures
import ipaddress
import hashlib
import json
import os
import shutil
import socket
import statistics
import subprocess
import tempfile
import time
from pathlib import Path
from urllib.parse import parse_qs, unquote, urlsplit

ROOT = Path(__file__).resolve().parent.parent
PUBLIC = ROOT / "public"
SOURCE = PUBLIC / "freewave-tcp.txt"
HISTORY_PATH = PUBLIC / "history.json"
HISTORY_RUNS = 10
XRAY = os.environ.get("XRAY_PATH", "xray")
CURL = shutil.which("curl")
MAX_TESTS = int(os.environ.get("FREEWAVE_MAX_XRAY_TESTS", "500"))
WORKERS = 8
LATENCY_URLS = (
    "https://www.gstatic.com/generate_204",
    "https://www.google.com/generate_204",
    "https://github.com/robots.txt",
    "https://example.com/",
)
DOWNLOAD_URL = "https://speed.cloudflare.com/__down?bytes=1048576"
DOWNLOAD_BYTES = 1048576
REQUIRED_HTTPS_PROBES = 3
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
    server_name = get(q, "sni", "servername", default=parsed.hostname)
    # When a CDN IP is used as the endpoint and the URI omits host=, use SNI
    # for the HTTP Host/authority instead of sending the raw IP to the CDN.
    try:
        ipaddress.ip_address(parsed.hostname)
        default_host = server_name
    except ValueError:
        default_host = parsed.hostname
    host_header = get(q, "host", default=default_host)
    fingerprint = get(q, "fp", default="chrome")
    flow = get(q, "flow")
    encryption = get(q, "encryption", default="none")
    # One upstream concatenates a node label onto the "none" encryption value.
    # Strip only this known malformed suffix; keep all other values for Xray validation.
    if encryption.lower().startswith("none@shadowsocks"):
        encryption = "none"
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
        authority = get(q, "authority", "host", default=default_host)
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

                latency_samples = []
                successful_probe_urls = []
                # Stop after three successful independent probes; later endpoints are fallbacks.
                for endpoint in LATENCY_URLS:
                    if len(latency_samples) >= REQUIRED_HTTPS_PROBES:
                        break
                    try:
                        status, values, curl_error = curl_request(
                            local_port, endpoint, 10, "%{http_code}\t%{time_total}"
                        )
                        if 200 <= status < 400 and values and values[0] > 0:
                            latency_samples.append(values[0] * 1000)
                            successful_probe_urls.append(endpoint)
                        else:
                            last_error = f"HTTPS probe returned HTTP {status}: {curl_error or 'no response'}"
                    except (subprocess.TimeoutExpired, OSError) as exc:
                        last_error = f"HTTPS probe failed: {str(exc)[:120]}"

                if len(latency_samples) < REQUIRED_HTTPS_PROBES:
                    last_error = (
                        f"Only {len(latency_samples)}/{REQUIRED_HTTPS_PROBES} independent HTTPS probes passed. "
                        + last_error
                    )
                    log_handle.flush()
                    runtime_lines = [
                        line.strip()
                        for line in log_path.read_text(encoding="utf-8", errors="replace").splitlines()
                        if line.strip()
                    ]
                    if runtime_lines:
                        last_error += " | Xray: " + " | ".join(runtime_lines[-2:])[-360:]
                    continue

                latency_ms = statistics.median(latency_samples)
                median_deviation = statistics.median(
                    abs(sample - latency_ms) for sample in latency_samples
                )
                jitter_ms = round(median_deviation, 2)
                stability_score = 1.0 / (1.0 + median_deviation / max(latency_ms, 1.0))

                speed_mbps = None
                speed_test_bytes = 0
                try:
                    status, values, _curl_error = curl_request(
                        local_port,
                        DOWNLOAD_URL,
                        15,
                        "%{http_code}\t%{speed_download}\t%{size_download}",
                    )
                    if (
                        status == 200
                        and len(values) >= 2
                        and values[0] > 0
                        and int(values[1]) >= DOWNLOAD_BYTES
                    ):
                        speed_mbps = values[0] * 8 / 1_000_000
                        speed_test_bytes = int(values[1])
                except (subprocess.TimeoutExpired, OSError):
                    pass

                return {
                    "uri": uri, "ok": True, "host": host, "port": port,
                    "latency_ms": round(latency_ms, 2),
                    "jitter_ms": jitter_ms,
                    "stability_score": round(stability_score, 4),
                    "latency_samples_ms": [round(value, 2) for value in latency_samples],
                    "confirmed_https_probes": len(successful_probe_urls),
                    "probe_urls": successful_probe_urls,
                    "speed_mbps": round(speed_mbps, 3) if speed_mbps is not None else None,
                    "speed_test_bytes": speed_test_bytes,
                    "speed_test_ok": speed_mbps is not None and speed_test_bytes >= DOWNLOAD_BYTES,
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


def load_history() -> list[dict]:
    """Load the last scan observations; malformed history is safely ignored."""
    try:
        data = json.loads(HISTORY_PATH.read_text(encoding="utf-8"))
        runs = data.get("runs", []) if isinstance(data, dict) else []
        return [run for run in runs if isinstance(run, dict)][-(HISTORY_RUNS - 1):]
    except (OSError, json.JSONDecodeError, TypeError):
        return []


def node_fingerprint(uri: str) -> str:
    # Store only a one-way identifier: never copy public VLESS credentials into history.
    return hashlib.sha256(uri.encode("utf-8")).hexdigest()


def update_history(history: list[dict], results: list[dict]) -> list[dict]:
    nodes = []
    for item in results:
        uri = item.get("uri")
        if not uri:
            continue
        nodes.append({
            "id": node_fingerprint(uri),
            "ok": bool(item.get("ok")),
            "latency_ms": item.get("latency_ms"),
            "speed_mbps": item.get("speed_mbps"),
        })
    run = {
        "generated_at_utc": time.strftime("%Y-%m-%d %H:%M:%S UTC", time.gmtime()),
        "tested_count": len(nodes),
        "validated_count": sum(1 for node in nodes if node["ok"]),
        "nodes": nodes,
    }
    updated = (history + [run])[-HISTORY_RUNS:]
    HISTORY_PATH.write_text(
        json.dumps({"schema_version": 1, "runs": updated}, indent=2) + "\\n",
        encoding="utf-8",
    )
    return updated


def history_reliability(history: list[dict]) -> dict[str, tuple[int, int]]:
    observations: dict[str, list[int]] = {}
    for run in history:
        for node in run.get("nodes", []):
            node_id = node.get("id")
            if isinstance(node_id, str) and isinstance(node.get("ok"), bool):
                observations.setdefault(node_id, []).append(1 if node["ok"] else 0)
    return {
        node_id: (sum(values), len(values))
        for node_id, values in observations.items()
    }


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
    if len(candidates) <= MAX_TESTS:
        selected = candidates
    elif MAX_TESTS <= 1:
        selected = candidates[:1]
    else:
        # Spread probes across the complete upstream list instead of favoring
        # the first entries, which may all belong to one stale server batch.
        indices = [
            round(index * (len(candidates) - 1) / (MAX_TESTS - 1))
            for index in range(MAX_TESTS)
        ]
        selected = [candidates[index] for index in indices]
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

    history = update_history(load_history(), results)
    reliability = history_reliability(history)
    for item in results:
        successes, attempts = reliability.get(node_fingerprint(item["uri"]), (0, 0))
        item["history_attempts"] = attempts
        item["history_successes"] = successes
        item["history_success_rate"] = round(successes / attempts, 4) if attempts >= 2 else None

    passed = [item for item in results if item.get("ok")]
    speeds = [item["speed_mbps"] for item in passed if item.get("speed_mbps") is not None]
    min_latency = min((item["latency_ms"] for item in passed), default=1)
    max_speed = max(speeds, default=0)
    for item in passed:
        latency_score = min_latency / max(item["latency_ms"], 1)
        speed = item.get("speed_mbps") or 0
        speed_score = speed / max_speed if max_speed else 0
        stability_score = item.get("stability_score", 0)
        # Prefer fast nodes, while still rewarding low response time and consistency.
        current_score = 0.55 * speed_score + 0.30 * latency_score + 0.15 * stability_score
        # Do not punish new nodes. Once there are at least two observations,
        # gently reduce the score of nodes that have repeatedly failed validation.
        success_rate = item.get("history_success_rate")
        reliability_factor = 0.75 + 0.25 * success_rate if success_rate is not None else 1.0
        item["score"] = round(current_score * reliability_factor, 4)
        item["reliability_factor"] = round(reliability_factor, 4)

    passed.sort(
        key=lambda x: (x.get("score", 0), x.get("speed_mbps") or 0, -x["latency_ms"]),
        reverse=True,
    )

    if passed:
        full = "\n".join(item["uri"] for item in passed) + "\n"
        (PUBLIC / "freewave-vless.txt").write_text(full, encoding="utf-8")
        best_text = passed[0]["uri"] + "\n"
        (PUBLIC / "freewave-best.txt").write_text(best_text, encoding="utf-8")
        (PUBLIC / "freewave-best-base64.txt").write_text(
            base64.b64encode(best_text.encode()).decode() + "\n",
            encoding="utf-8",
        )
        (PUBLIC / "freewave-vless-base64.txt").write_text(
            base64.b64encode(full.encode()).decode() + "\n", encoding="utf-8"
        )
        fast_text = "\n".join(item["uri"] for item in passed[:25]) + "\n"
        (PUBLIC / "freewave-fast.txt").write_text(fast_text, encoding="utf-8")
        (PUBLIC / "freewave-fast-base64.txt").write_text(
            base64.b64encode(fast_text.encode()).decode() + "\n",
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
            "freewave-best.txt",
            "freewave-best-base64.txt",
            "freewave-fast.txt",
            "freewave-fast-base64.txt",
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
        "required_https_probes_per_node": REQUIRED_HTTPS_PROBES,
        "download_probe_bytes": DOWNLOAD_BYTES,
        "history_window_runs": len(history),
        "results": [
            {key: item.get(key) for key in (
                "host", "port", "ok", "latency_ms", "jitter_ms", "stability_score",
                "speed_mbps", "score", "speed_test_ok", "speed_test_bytes", "confirmed_https_probes",
                "latency_samples_ms", "probe_urls", "history_attempts",
                "history_successes", "history_success_rate", "reliability_factor", "error"
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
        f"- Nodes confirmed by at least {REQUIRED_HTTPS_PROBES} separate HTTPS probes: {sum(1 for x in passed if x.get('confirmed_https_probes', 0) >= REQUIRED_HTTPS_PROBES)}",
        f"- Successful {DOWNLOAD_BYTES // 1024} KiB download tests: {sum(1 for x in passed if x.get('speed_test_ok'))}",
        "", "## Top nodes from this GitHub Actions run", "",
        "| # | Server | Median latency | Jitter (MAD) | Download | History | Score |",
        "|---:|---|---:|---:|---:|---:|---:|",
    ]
    for index, item in enumerate(passed[:10], 1):
        speed = f"{item['speed_mbps']:.2f} Mbps" if item.get("speed_mbps") is not None else "not measured"
        attempts = item.get("history_attempts", 0)
        successes = item.get("history_successes", 0)
        history_label = f"{successes}/{attempts}" if attempts >= 2 else "new"
        lines.append(
            f"| {index} | {item['host']}:{item['port']} | "
            f"{item['latency_ms']:.0f} ms | {item.get('jitter_ms', 0):.1f} ms | "
            f"{speed} | {history_label} | {item.get('score', 0):.3f} |"
        )
    lines += [
        "", "## Subscription files", "",
        "- freewave-vless.txt — nodes passing two independent HTTPS probes through Xray",
        "- freewave-best.txt — the single highest-ranked VLESS key for clients that import one key",
        "- freewave-best-base64.txt — Base64 version of the single best key",
        "- freewave-fast.txt — top 25 by relative score",
        "- freewave-fast-base64.txt — Base64 version of the top 25",
        "- freewave-vless-base64.txt — Base64 version of validated nodes",
        "- freewave-tcp.txt — preliminary TCP-only candidates",
        "- metrics.json — detailed test results and rolling reliability scores",
        "- history.json — last 10 scan outcomes per hashed node identifier; contains no VLESS links",
        "",
        "> Measurements are from GitHub Actions, not from your phone. Your ISP,",
        "> country, route, time of day, and client settings can change the result.",
        "> The small download test is a snapshot, not a sustained-speed or unlimited-traffic guarantee.",
        "> Two successful HTTPS probes verify two HTTPS destinations through the VLESS tunnel at test time;",
        "> they do not prove all sites/apps, UDP support, privacy, or future uptime.",
        "> Ranking weights: 55% measured download rate, 30% median HTTPS latency, 15% latency stability (median absolute deviation).",
        "> After two observations, a rolling reliability multiplier gently penalizes nodes that repeatedly fail; new nodes are not penalized.",
        "> Reliability history covers at most the last 10 scans and stores SHA-256 identifiers, not VLESS credentials.",
        "> Latency is the median of successful independent HTTPS probes; at least three must pass.",
        "> Download rate uses curl's measured average transfer rate from a 1 MiB probe, not first-byte latency.",
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
