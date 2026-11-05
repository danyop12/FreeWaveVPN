# FreeWave VPN status

- Last run: 2026-11-05 01:06:16 UTC
- TCP candidates: 429
- Tested through Xray: 429
- Passed real HTTPS request through VLESS: 260
- Failed VLESS test: 169
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 260
- Successful 1024 KiB download tests: 212
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.206:23576 | 61 ms | 2.4 ms | 54.86 Mbps | 3/3 | 0.994 |
| 2 | 195.211.98.43:443 | 235 ms | 1.6 ms | 13.91 Mbps | 3/3 | 0.366 |
| 3 | 195.123.240.65:443 | 211 ms | 5.7 ms | 13.29 Mbps | 2/2 | 0.365 |
| 4 | 167.17.69.171:443 | 292 ms | 1.2 ms | 12.14 Mbps | 3/3 | 0.333 |
| 5 | 185.95.231.156:443 | 297 ms | 3.6 ms | 12.21 Mbps | 3/3 | 0.332 |
| 6 | 167.17.69.171:443 | 299 ms | 5.0 ms | 12.03 Mbps | 3/3 | 0.329 |
| 7 | 167.17.69.171:443 | 296 ms | 8.8 ms | 12.16 Mbps | 3/3 | 0.329 |
| 8 | 167.17.69.171:443 | 293 ms | 4.3 ms | 11.89 Mbps | 3/3 | 0.329 |
| 9 | 169.40.42.163:443 | 297 ms | 2.9 ms | 11.87 Mbps | 3/3 | 0.329 |
| 10 | 195.123.235.183:443 | 296 ms | 5.5 ms | 11.90 Mbps | 3/3 | 0.328 |

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
