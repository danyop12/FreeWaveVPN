# FreeWave VPN status

- Last run: 2026-11-06 06:58:24 UTC
- TCP candidates: 429
- Tested through Xray: 429
- Passed real HTTPS request through VLESS: 157
- Failed VLESS test: 272
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 157
- Successful 1024 KiB download tests: 129
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.214:23576 | 146 ms | 0.6 ms | 24.05 Mbps | new | 0.997 |
| 2 | ww13.levikogjgfdd.ir:23576 | 148 ms | 0.9 ms | 24.14 Mbps | new | 0.996 |
| 3 | 15.204.97.206:23576 | 146 ms | 0.3 ms | 23.80 Mbps | 5/5 | 0.991 |
| 4 | 195.211.98.43:443 | 237 ms | 3.1 ms | 15.11 Mbps | new | 0.677 |
| 5 | 159.89.87.21:28190 | 250 ms | 4.3 ms | 14.22 Mbps | 4/4 | 0.647 |
| 6 | 185.95.231.156:443 | 248 ms | 1.3 ms | 14.02 Mbps | 5/5 | 0.645 |
| 7 | 185.95.231.156:443 | 249 ms | 2.1 ms | 13.83 Mbps | new | 0.640 |
| 8 | 159.89.87.21:28190 | 250 ms | 0.5 ms | 13.76 Mbps | 3/3 | 0.639 |
| 9 | 159.89.87.21:28190 | 248 ms | 1.1 ms | 13.59 Mbps | new | 0.636 |
| 10 | 159.89.87.21:28190 | 264 ms | 1.2 ms | 13.90 Mbps | 4/4 | 0.632 |

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
