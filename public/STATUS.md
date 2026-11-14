# FreeWave VPN status

- Last run: 2026-11-14 18:11:46 UTC
- TCP candidates: 378
- Tested through Xray: 378
- Passed real HTTPS request through VLESS: 148
- Failed VLESS test: 230
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 148
- Successful 1024 KiB download tests: 131
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 167.17.69.171:443 | 72 ms | 7.5 ms | 56.16 Mbps | 6/6 | 0.894 |
| 2 | 185.95.231.156:443 | 86 ms | 2.7 ms | 57.32 Mbps | 5/5 | 0.879 |
| 3 | 167.17.69.171:443 | 66 ms | 2.6 ms | 51.58 Mbps | 3/3 | 0.876 |
| 4 | 159.89.87.21:28190 | 60 ms | 6.6 ms | 48.87 Mbps | 5/5 | 0.864 |
| 5 | 167.17.69.171:443 | 68 ms | 0.2 ms | 44.71 Mbps | 4/4 | 0.809 |
| 6 | 185.95.231.156:443 | 86 ms | 5.0 ms | 50.13 Mbps | 4/4 | 0.805 |
| 7 | 167.17.69.171:443 | 66 ms | 2.0 ms | 42.42 Mbps | 4/4 | 0.792 |
| 8 | 167.17.69.171:443 | 72 ms | 5.9 ms | 44.68 Mbps | 2/2 | 0.787 |
| 9 | 167.17.69.171:443 | 73 ms | 9.7 ms | 45.31 Mbps | 5/5 | 0.782 |
| 10 | 169.40.42.168:443 | 142 ms | 17.9 ms | 55.09 Mbps | 2/2 | 0.772 |

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
