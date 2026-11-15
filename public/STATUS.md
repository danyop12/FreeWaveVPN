# FreeWave VPN status

- Last run: 2026-11-15 06:45:04 UTC
- TCP candidates: 406
- Tested through Xray: 406
- Passed real HTTPS request through VLESS: 209
- Failed VLESS test: 197
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 209
- Successful 1024 KiB download tests: 167
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 56 ms | 1.7 ms | 73.75 Mbps | 2/2 | 0.990 |
| 2 | 159.89.87.21:28190 | 75 ms | 0.4 ms | 69.65 Mbps | 6/6 | 0.888 |
| 3 | 159.89.87.21:28190 | 78 ms | 14.9 ms | 72.62 Mbps | 3/3 | 0.879 |
| 4 | 167.17.69.171:443 | 63 ms | 0.4 ms | 56.33 Mbps | 5/5 | 0.830 |
| 5 | 185.95.231.156:443 | 57 ms | 1.8 ms | 52.10 Mbps | 2/2 | 0.822 |
| 6 | 167.17.69.171:443 | 64 ms | 0.7 ms | 51.30 Mbps | 2/2 | 0.788 |
| 7 | 159.89.87.21:28190 | 58 ms | 2.6 ms | 47.17 Mbps | 4/4 | 0.782 |
| 8 | 169.40.42.168:443 | 76 ms | 2.8 ms | 54.01 Mbps | 4/4 | 0.766 |
| 9 | 167.17.69.171:443 | 65 ms | 0.2 ms | 45.01 Mbps | 3/3 | 0.738 |
| 10 | 169.40.42.35:443 | 70 ms | 0.4 ms | 40.59 Mbps | 2/2 | 0.687 |

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
