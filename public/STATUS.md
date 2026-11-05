# FreeWave VPN status

- Last run: 2026-11-05 06:57:52 UTC
- TCP candidates: 434
- Tested through Xray: 434
- Passed real HTTPS request through VLESS: 214
- Failed VLESS test: 220
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 214
- Successful 1024 KiB download tests: 160
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.206:23576 | 89 ms | 1.0 ms | 43.36 Mbps | 4/4 | 0.919 |
| 2 | ww13.levikogjgfdd.ir:23576 | 88 ms | 0.8 ms | 41.53 Mbps | new | 0.898 |
| 3 | 15.204.97.219:23576 | 87 ms | 0.4 ms | 40.52 Mbps | new | 0.890 |
| 4 | 195.123.240.65:443 | 116 ms | 1.6 ms | 44.35 Mbps | new | 0.875 |
| 5 | 188.114.98.0:443 | 69 ms | 4.2 ms | not measured | new | 0.442 |
| 6 | 195.211.98.43:443 | 223 ms | 1.5 ms | 15.94 Mbps | new | 0.439 |
| 7 | 185.95.231.156:443 | 280 ms | 2.8 ms | 12.34 Mbps | 4/4 | 0.375 |
| 8 | 185.95.231.156:443 | 293 ms | 7.6 ms | 12.72 Mbps | new | 0.374 |
| 9 | 159.89.87.21:28190 | 292 ms | 2.4 ms | 11.98 Mbps | new | 0.368 |
| 10 | 159.89.87.21:28190 | 290 ms | 4.0 ms | 11.96 Mbps | 4/4 | 0.368 |

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
