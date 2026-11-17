# FreeWave VPN status

- Last run: 2026-11-17 08:04:48 UTC
- TCP candidates: 420
- Tested through Xray: 420
- Passed real HTTPS request through VLESS: 109
- Failed VLESS test: 311
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 109
- Successful 1024 KiB download tests: 91
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 159.89.87.21:28190 | 59 ms | 1.0 ms | 72.69 Mbps | 4/4 | 0.956 |
| 2 | 159.89.87.21:28190 | 50 ms | 4.5 ms | 67.18 Mbps | 6/6 | 0.946 |
| 3 | 159.89.87.21:28190 | 53 ms | 3.1 ms | 67.38 Mbps | 3/3 | 0.935 |
| 4 | 159.89.87.21:28190 | 60 ms | 4.8 ms | 64.69 Mbps | 5/5 | 0.881 |
| 5 | 169.40.42.74:443 | 69 ms | 4.0 ms | 56.43 Mbps | 6/6 | 0.787 |
| 6 | 169.40.42.231:443 | 102 ms | 34.2 ms | 58.38 Mbps | new | 0.702 |
| 7 | 169.40.42.89:443 | 114 ms | 47.8 ms | 59.41 Mbps | 4/4 | 0.688 |
| 8 | 169.40.42.89:443 | 188 ms | 29.0 ms | 61.07 Mbps | 5/5 | 0.672 |
| 9 | 167.17.69.171:443 | 77 ms | 1.5 ms | 40.96 Mbps | 2/2 | 0.653 |
| 10 | 167.17.69.171:443 | 82 ms | 6.5 ms | 39.41 Mbps | 6/6 | 0.621 |

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
