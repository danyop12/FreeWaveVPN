# FreeWave VPN status

- Last run: 2026-11-24 10:04:03 UTC
- TCP candidates: 402
- Tested through Xray: 402
- Passed real HTTPS request through VLESS: 147
- Failed VLESS test: 255
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 147
- Successful 1024 KiB download tests: 121
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 148.135.86.162:10000 | 828 ms | 0.4 ms | 29.10 Mbps | new | 0.751 |
| 2 | 195.123.235.183:443 | 209 ms | 4.3 ms | 16.64 Mbps | new | 0.666 |
| 3 | 169.40.42.231:443 | 220 ms | 1.9 ms | 16.98 Mbps | 4/4 | 0.663 |
| 4 | 195.123.235.183:443 | 216 ms | 13.4 ms | 16.59 Mbps | new | 0.652 |
| 5 | 169.40.42.229:443 | 225 ms | 9.7 ms | 16.28 Mbps | 3/3 | 0.641 |
| 6 | 169.40.42.90:443 | 218 ms | 4.2 ms | 15.62 Mbps | 5/5 | 0.638 |
| 7 | 169.40.42.89:443 | 220 ms | 3.4 ms | 15.21 Mbps | 2/2 | 0.629 |
| 8 | 169.40.42.163:443 | 218 ms | 4.7 ms | 14.94 Mbps | 3/3 | 0.625 |
| 9 | 169.40.42.89:443 | 288 ms | 0.1 ms | 16.94 Mbps | 6/6 | 0.619 |
| 10 | 169.40.42.89:443 | 272 ms | 15.5 ms | 16.19 Mbps | 2/2 | 0.605 |

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
