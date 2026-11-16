# FreeWave VPN status

- Last run: 2026-11-16 06:40:50 UTC
- TCP candidates: 409
- Tested through Xray: 409
- Passed real HTTPS request through VLESS: 151
- Failed VLESS test: 258
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 151
- Successful 1024 KiB download tests: 123
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.214:23576 | 51 ms | 2.0 ms | 72.56 Mbps | 3/3 | 0.994 |
| 2 | 15.204.97.219:23576 | 53 ms | 1.7 ms | 20.99 Mbps | new | 0.593 |
| 3 | 104.21.236.116:443 | 108 ms | 14.2 ms | 41.84 Mbps | new | 0.592 |
| 4 | 195.123.240.65:443 | 214 ms | 36.6 ms | 22.31 Mbps | 3/3 | 0.369 |
| 5 | 172.67.235.78:443 | 95 ms | 1.6 ms | 4.34 Mbps | new | 0.342 |
| 6 | 104.18.79.242:443 | 101 ms | 9.5 ms | 4.61 Mbps | new | 0.323 |
| 7 | 188.114.98.0:443 | 106 ms | 1.6 ms | 4.10 Mbps | new | 0.323 |
| 8 | 167.17.69.171:443 | 276 ms | 0.8 ms | 11.92 Mbps | new | 0.295 |
| 9 | 167.17.69.171:443 | 287 ms | 1.5 ms | 11.66 Mbps | 5/5 | 0.291 |
| 10 | 167.17.69.171:443 | 280 ms | 5.7 ms | 11.73 Mbps | 4/4 | 0.290 |

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
