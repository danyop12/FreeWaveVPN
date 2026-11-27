# FreeWave VPN status

- Last run: 2026-11-27 08:45:16 UTC
- TCP candidates: 411
- Tested through Xray: 411
- Passed real HTTPS request through VLESS: 149
- Failed VLESS test: 262
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 149
- Successful 1024 KiB download tests: 112
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.214:23576 | 50 ms | 0.9 ms | 79.69 Mbps | 5/5 | 0.997 |
| 2 | 15.204.97.214:23576 | 54 ms | 1.7 ms | 62.28 Mbps | 2/2 | 0.853 |
| 3 | 38.145.220.193:443 | 126 ms | 6.5 ms | 24.50 Mbps | new | 0.431 |
| 4 | 195.211.98.43:443 | 210 ms | 1.9 ms | 16.29 Mbps | 4/4 | 0.333 |
| 5 | 188.114.98.0:443 | 88 ms | 0.9 ms | not measured | new | 0.319 |
| 6 | 172.64.154.113:8443 | 93 ms | 2.6 ms | not measured | new | 0.306 |
| 7 | 188.114.98.0:443 | 100 ms | 2.1 ms | not measured | new | 0.297 |
| 8 | 185.95.231.156:443 | 270 ms | 4.0 ms | 12.31 Mbps | 3/3 | 0.288 |
| 9 | 188.114.98.0:443 | 107 ms | 3.6 ms | not measured | new | 0.285 |
| 10 | 188.114.98.0:443 | 102 ms | 10.0 ms | not measured | new | 0.284 |

## Subscription files

- freewave-vless.txt — nodes passing two independent HTTPS probes through Xray
- freewave-best.txt — the single highest-ranked VLESS key for clients that import one key
- freewave-best-base64.txt — Base64 version of the single best key
- freewave-fast.txt — top 25 by relative score
- freewave-fast-base64.txt — Base64 version of the top 25
- freewave-vless-base64.txt — Base64 version of validated nodes
- freewave-albania.txt — validated nodes whose endpoint IP geolocates to Albania (AL)
- freewave-albania-base64.txt — Base64 version of the Albania-only feed
- freewave-tcp.txt — preliminary TCP-only candidates
- metrics.json — detailed test results and rolling reliability scores
- history.json — last 10 scan outcomes per hashed node identifier; contains no VLESS links

> Measurements are from GitHub Actions, not from your phone. Your ISP,
> country, route, time of day, and client settings can change the result.
> The small download test is a snapshot, not a sustained-speed or unlimited-traffic guarantee.
> Two successful HTTPS probes verify two HTTPS destinations through the VLESS tunnel at test time;
> they do not prove all sites/apps, UDP support, privacy, or future uptime.
> Ranking weights: 55% measured download rate, 30% median HTTPS latency, 15% latency stability (median absolute deviation).
> After two observations, a rolling reliability multiplier gently penalizes nodes that repeatedly fail; new nodes are not penalized.
> Reliability history covers at most the last 10 scans and stores SHA-256 identifiers, not VLESS credentials.
> Latency is the median of successful independent HTTPS probes; at least three must pass.
> Download rate uses curl's measured average transfer rate from a 1 MiB probe, not first-byte latency.
> Public nodes are third-party infrastructure and may log metadata or disappear.
