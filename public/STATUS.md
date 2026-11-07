# FreeWave VPN status

- Last run: 2026-11-07 21:59:28 UTC
- TCP candidates: 415
- Tested through Xray: 415
- Passed real HTTPS request through VLESS: 221
- Failed VLESS test: 194
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 221
- Successful 1024 KiB download tests: 166
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 78 ms | 1.6 ms | 47.62 Mbps | 2/2 | 0.997 |
| 2 | 15.204.97.219:23576 | 91 ms | 4.9 ms | 41.69 Mbps | 2/2 | 0.881 |
| 3 | ww13.levikogjgfdd.ir:23576 | 88 ms | 1.6 ms | 39.14 Mbps | 4/4 | 0.865 |
| 4 | 195.123.240.65:443 | 116 ms | 10.6 ms | 31.68 Mbps | 7/7 | 0.705 |
| 5 | 195.123.240.65:443 | 212 ms | 135.2 ms | 43.20 Mbps | 3/4 | 0.657 |
| 6 | 195.211.98.43:443 | 228 ms | 8.6 ms | 15.60 Mbps | 3/3 | 0.427 |
| 7 | 195.211.98.43:443 | 239 ms | 14.4 ms | 16.02 Mbps | 2/2 | 0.424 |
| 8 | 108.61.119.63:18580 | 227 ms | 21.8 ms | 15.96 Mbps | 4/4 | 0.424 |
| 9 | 167.17.69.171:443 | 296 ms | 4.7 ms | 12.37 Mbps | 5/5 | 0.369 |
| 10 | 167.17.69.171:443 | 297 ms | 3.9 ms | 12.12 Mbps | 6/6 | 0.367 |

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
