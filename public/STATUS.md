# FreeWave VPN status

- Last run: 2026-11-25 08:45:58 UTC
- TCP candidates: 411
- Tested through Xray: 411
- Passed real HTTPS request through VLESS: 156
- Failed VLESS test: 255
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 156
- Successful 1024 KiB download tests: 129
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 148.135.86.162:10000 | 779 ms | 17.1 ms | 42.87 Mbps | new | 0.730 |
| 2 | ww13.levikogjgfdd.ir:23576 | 147 ms | 1.2 ms | 24.57 Mbps | 4/4 | 0.642 |
| 3 | 195.123.240.65:443 | 119 ms | 20.4 ms | 16.70 Mbps | 2/2 | 0.562 |
| 4 | 195.123.240.65:443 | 624 ms | 500.6 ms | 35.16 Mbps | 5/6 | 0.552 |
| 5 | 172.64.154.113:8443 | 87 ms | 8.1 ms | not measured | new | 0.437 |
| 6 | 159.89.87.21:28190 | 287 ms | 0.3 ms | 14.56 Mbps | new | 0.428 |
| 7 | 172.64.154.113:8443 | 92 ms | 3.7 ms | not measured | new | 0.428 |
| 8 | 159.89.87.21:28190 | 273 ms | 10.6 ms | 14.17 Mbps | 2/2 | 0.422 |
| 9 | 159.89.87.21:28190 | 247 ms | 2.4 ms | 12.80 Mbps | 2/2 | 0.419 |
| 10 | 159.89.87.21:28190 | 279 ms | 15.2 ms | 13.81 Mbps | 3/3 | 0.413 |

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
