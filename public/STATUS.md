# FreeWave VPN status

- Last run: 2026-11-08 22:16:30 UTC
- TCP candidates: 414
- Tested through Xray: 414
- Passed real HTTPS request through VLESS: 267
- Failed VLESS test: 147
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 267
- Successful 1024 KiB download tests: 213
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 108.61.119.63:18580 | 144 ms | 21.0 ms | 28.84 Mbps | 2/2 | 0.981 |
| 2 | ww13.levikogjgfdd.ir:23576 | 153 ms | 8.2 ms | 23.29 Mbps | 3/3 | 0.868 |
| 3 | 195.123.240.65:443 | 181 ms | 7.4 ms | 21.46 Mbps | 3/3 | 0.791 |
| 4 | 195.211.98.43:443 | 167 ms | 3.8 ms | 14.85 Mbps | 4/4 | 0.688 |
| 5 | 195.123.235.183:443 | 210 ms | 2.2 ms | 16.73 Mbps | 3/3 | 0.673 |
| 6 | 195.123.235.183:443 | 207 ms | 1.0 ms | 15.90 Mbps | 2/2 | 0.661 |
| 7 | 185.95.231.156:443 | 224 ms | 4.4 ms | 16.74 Mbps | 3/3 | 0.659 |
| 8 | 66.70.179.198:2053 | 225 ms | 8.7 ms | 16.54 Mbps | 5/5 | 0.651 |
| 9 | 66.70.179.198:2053 | 222 ms | 1.0 ms | 16.05 Mbps | new | 0.649 |
| 10 | 185.95.231.156:443 | 222 ms | 4.8 ms | 16.11 Mbps | 3/3 | 0.648 |

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
