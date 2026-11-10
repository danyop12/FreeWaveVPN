# FreeWave VPN status

- Last run: 2026-11-10 19:51:47 UTC
- TCP candidates: 420
- Tested through Xray: 420
- Passed real HTTPS request through VLESS: 182
- Failed VLESS test: 238
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 182
- Successful 1024 KiB download tests: 145
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 159.89.87.21:28190 | 55 ms | 2.8 ms | 68.89 Mbps | 2/2 | 0.975 |
| 2 | 159.89.87.21:28190 | 58 ms | 6.7 ms | 70.48 Mbps | 5/5 | 0.963 |
| 3 | 159.89.87.21:28190 | 79 ms | 2.5 ms | 69.77 Mbps | 6/6 | 0.895 |
| 4 | 159.89.87.21:28190 | 65 ms | 12.0 ms | 61.19 Mbps | 2/2 | 0.853 |
| 5 | 167.17.69.171:443 | 77 ms | 5.9 ms | 45.49 Mbps | 7/7 | 0.704 |
| 6 | 167.17.69.171:443 | 93 ms | 3.1 ms | 47.44 Mbps | 3/3 | 0.690 |
| 7 | 169.40.42.89:443 | 76 ms | 0.6 ms | 36.64 Mbps | 2/2 | 0.647 |
| 8 | 159.89.87.21:28190 | 54 ms | 0.4 ms | 23.68 Mbps | 3/3 | 0.634 |
| 9 | 185.95.231.156:443 | 86 ms | 2.4 ms | 38.19 Mbps | 3/3 | 0.633 |
| 10 | 185.95.231.156:443 | 90 ms | 6.6 ms | 38.45 Mbps | 4/4 | 0.621 |

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
