# FreeWave VPN status

- Last run: 2026-11-07 06:08:00 UTC
- TCP candidates: 438
- Tested through Xray: 438
- Passed real HTTPS request through VLESS: 190
- Failed VLESS test: 248
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 190
- Successful 1024 KiB download tests: 153
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | ww13.levikogjgfdd.ir:23576 | 58 ms | 2.6 ms | 19.21 Mbps | 3/3 | 0.994 |
| 2 | 195.211.98.43:443 | 291 ms | 57.8 ms | 16.16 Mbps | 2/2 | 0.647 |
| 3 | 195.123.240.65:443 | 187 ms | 5.8 ms | 11.29 Mbps | 6/6 | 0.562 |
| 4 | 167.17.69.171:443 | 307 ms | 9.2 ms | 12.40 Mbps | 2/2 | 0.557 |
| 5 | 167.17.69.171:443 | 319 ms | 7.2 ms | 12.34 Mbps | 4/4 | 0.554 |
| 6 | 66.70.179.198:2053 | 294 ms | 2.9 ms | 12.05 Mbps | 3/3 | 0.553 |
| 7 | 167.17.69.171:443 | 313 ms | 15.6 ms | 12.25 Mbps | 5/5 | 0.549 |
| 8 | 159.89.87.21:28190 | 303 ms | 0.9 ms | 11.67 Mbps | new | 0.541 |
| 9 | 169.40.42.89:443 | 355 ms | 52.7 ms | 12.60 Mbps | new | 0.540 |
| 10 | 159.89.87.21:28190 | 310 ms | 9.8 ms | 11.73 Mbps | new | 0.537 |

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
