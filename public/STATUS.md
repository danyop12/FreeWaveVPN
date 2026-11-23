# FreeWave VPN status

- Last run: 2026-11-23 07:49:37 UTC
- TCP candidates: 379
- Tested through Xray: 379
- Passed real HTTPS request through VLESS: 199
- Failed VLESS test: 180
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 199
- Successful 1024 KiB download tests: 165
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 51 ms | 4.5 ms | 66.89 Mbps | 4/4 | 0.958 |
| 2 | 159.89.87.21:28190 | 48 ms | 1.1 ms | 63.01 Mbps | 3/3 | 0.954 |
| 3 | 159.89.87.21:28190 | 49 ms | 0.1 ms | 58.86 Mbps | 6/6 | 0.913 |
| 4 | 159.89.87.21:28190 | 50 ms | 3.0 ms | 60.31 Mbps | 5/5 | 0.912 |
| 5 | 185.95.231.156:443 | 50 ms | 0.0 ms | 58.43 Mbps | 3/3 | 0.906 |
| 6 | 185.95.231.156:443 | 79 ms | 2.1 ms | 62.28 Mbps | 4/4 | 0.832 |
| 7 | 159.89.87.21:28190 | 67 ms | 16.1 ms | 57.93 Mbps | 2/2 | 0.802 |
| 8 | 169.40.42.202:443 | 75 ms | 4.7 ms | 50.70 Mbps | 3/3 | 0.742 |
| 9 | 169.40.42.89:443 | 111 ms | 17.3 ms | 55.12 Mbps | 5/5 | 0.707 |
| 10 | 169.40.42.168:443 | 69 ms | 1.1 ms | 41.93 Mbps | 2/2 | 0.692 |

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
