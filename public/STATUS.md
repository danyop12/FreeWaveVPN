# FreeWave VPN status

- Last run: 2026-11-11 19:46:29 UTC
- TCP candidates: 386
- Tested through Xray: 386
- Passed real HTTPS request through VLESS: 263
- Failed VLESS test: 123
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 263
- Successful 1024 KiB download tests: 200
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.219:23576 | 88 ms | 0.6 ms | 41.53 Mbps | 2/2 | 0.948 |
| 2 | 195.123.240.65:443 | 85 ms | 8.9 ms | 43.30 Mbps | 4/5 | 0.921 |
| 3 | 15.204.97.206:23576 | 98 ms | 10.6 ms | 40.43 Mbps | 2/2 | 0.895 |
| 4 | ww13.levikogjgfdd.ir:23576 | 97 ms | 0.6 ms | 38.02 Mbps | 3/3 | 0.880 |
| 5 | 195.123.240.65:443 | 284 ms | 200.2 ms | 41.79 Mbps | 3/4 | 0.659 |
| 6 | 162.159.198.1:443 | 80 ms | 7.1 ms | not measured | new | 0.438 |
| 7 | 188.114.98.0:443 | 87 ms | 15.9 ms | not measured | new | 0.403 |
| 8 | 195.123.240.65:443 | 286 ms | 199.0 ms | 17.98 Mbps | 2/2 | 0.401 |
| 9 | 167.17.69.171:443 | 288 ms | 1.1 ms | 12.07 Mbps | 3/3 | 0.386 |
| 10 | 172.67.74.177:443 | 98 ms | 8.2 ms | not measured | new | 0.384 |

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
