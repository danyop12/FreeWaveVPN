# FreeWave VPN status

- Last run: 2026-11-24 00:03:18 UTC
- TCP candidates: 374
- Tested through Xray: 374
- Passed real HTTPS request through VLESS: 191
- Failed VLESS test: 183
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 191
- Successful 1024 KiB download tests: 163
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 159.89.87.21:28190 | 70 ms | 2.4 ms | 56.95 Mbps | 2/2 | 0.983 |
| 2 | 159.89.87.21:28190 | 73 ms | 2.9 ms | 54.48 Mbps | 2/2 | 0.948 |
| 3 | 159.89.87.21:28190 | 77 ms | 8.0 ms | 55.87 Mbps | 4/4 | 0.935 |
| 4 | 159.89.87.21:28190 | 76 ms | 6.6 ms | 49.39 Mbps | 4/4 | 0.880 |
| 5 | 159.89.87.21:28190 | 68 ms | 3.4 ms | 45.47 Mbps | 2/2 | 0.878 |
| 6 | 159.89.87.21:28190 | 94 ms | 24.3 ms | 55.92 Mbps | 2/2 | 0.873 |
| 7 | 185.95.231.156:443 | 79 ms | 9.0 ms | 48.85 Mbps | 4/4 | 0.861 |
| 8 | 195.123.235.183:443 | 81 ms | 3.6 ms | 47.01 Mbps | new | 0.845 |
| 9 | 159.89.87.21:28190 | 77 ms | 7.0 ms | 39.76 Mbps | 4/4 | 0.782 |
| 10 | 195.123.235.183:443 | 86 ms | 6.4 ms | 38.91 Mbps | new | 0.749 |

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
