# FreeWave VPN status

- Last run: 2026-11-14 22:35:05 UTC
- TCP candidates: 387
- Tested through Xray: 387
- Passed real HTTPS request through VLESS: 252
- Failed VLESS test: 135
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 252
- Successful 1024 KiB download tests: 198
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.211.98.43:443 | 93 ms | 1.3 ms | 97.87 Mbps | 4/4 | 0.869 |
| 2 | 195.211.98.43:443 | 53 ms | 0.9 ms | 25.23 Mbps | new | 0.589 |
| 3 | 167.17.69.171:443 | 122 ms | 3.1 ms | 30.93 Mbps | 2/2 | 0.452 |
| 4 | 167.17.69.171:443 | 132 ms | 9.0 ms | 31.83 Mbps | 3/3 | 0.441 |
| 5 | 167.17.69.171:443 | 115 ms | 1.5 ms | 26.44 Mbps | 5/5 | 0.435 |
| 6 | 169.40.42.202:443 | 144 ms | 1.2 ms | 29.95 Mbps | 4/4 | 0.428 |
| 7 | 167.17.69.171:443 | 122 ms | 6.2 ms | 26.70 Mbps | 5/5 | 0.423 |
| 8 | 167.17.69.171:443 | 133 ms | 18.2 ms | 30.21 Mbps | 3/3 | 0.422 |
| 9 | 167.17.69.171:443 | 134 ms | 11.1 ms | 29.11 Mbps | 3/3 | 0.421 |
| 10 | 167.17.69.171:443 | 135 ms | 15.0 ms | 29.55 Mbps | 5/5 | 0.419 |

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
