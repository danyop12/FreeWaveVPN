# FreeWave VPN status

- Last run: 2026-11-05 00:27:42 UTC
- TCP candidates: 424
- Tested through Xray: 424
- Passed real HTTPS request through VLESS: 29
- Failed VLESS test: 395
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 29
- Successful 1024 KiB download tests: 21
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 79.141.164.83:443 | 300 ms | 58.8 ms | 8.58 Mbps | 3/3 | 0.854 |
| 2 | book.jarbook.net:443 | 308 ms | 37.9 ms | 8.23 Mbps | 3/3 | 0.835 |
| 3 | 79.141.164.83:443 | 303 ms | 61.5 ms | 7.91 Mbps | 3/3 | 0.809 |
| 4 | api.ferrin.uk:443 | 674 ms | 4.9 ms | 8.57 Mbps | 3/3 | 0.778 |
| 5 | api.ferrin.uk:443 | 666 ms | 0.2 ms | 8.19 Mbps | 3/3 | 0.756 |
| 6 | 79.141.164.125:443 | 322 ms | 79.0 ms | 6.52 Mbps | 3/3 | 0.705 |
| 7 | 89.110.64.15:443 | 478 ms | 2.9 ms | 6.85 Mbps | 3/3 | 0.701 |
| 8 | 51.158.206.25:23576 | 498 ms | 16.6 ms | 6.97 Mbps | 3/3 | 0.700 |
| 9 | 51.158.206.97:23576 | 488 ms | 8.9 ms | 6.90 Mbps | 3/3 | 0.699 |
| 10 | 79.141.164.125:443 | 519 ms | 94.3 ms | 7.12 Mbps | 3/3 | 0.687 |

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
