# FreeWave VPN status

- Last run: 2026-11-10 07:29:29 UTC
- TCP candidates: 430
- Tested through Xray: 430
- Passed real HTTPS request through VLESS: 189
- Failed VLESS test: 241
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 189
- Successful 1024 KiB download tests: 156
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | ww13.levikogjgfdd.ir:23576 | 159 ms | 15.2 ms | 21.68 Mbps | 3/3 | 0.979 |
| 2 | 195.211.98.43:443 | 155 ms | 0.3 ms | 14.21 Mbps | 4/4 | 0.810 |
| 3 | 185.95.231.156:443 | 203 ms | 5.9 ms | 17.06 Mbps | new | 0.807 |
| 4 | 195.211.98.43:443 | 159 ms | 2.9 ms | 14.43 Mbps | 4/4 | 0.806 |
| 5 | 195.123.235.183:443 | 218 ms | 2.5 ms | 17.12 Mbps | 3/3 | 0.795 |
| 6 | 172.67.199.126:2053 | 327 ms | 55.5 ms | 19.14 Mbps | new | 0.756 |
| 7 | 185.95.231.156:443 | 239 ms | 12.8 ms | 16.32 Mbps | 3/3 | 0.751 |
| 8 | 172.67.199.126:2053 | 283 ms | 7.9 ms | 16.17 Mbps | new | 0.720 |
| 9 | 167.17.69.171:443 | 261 ms | 22.5 ms | 15.64 Mbps | 4/4 | 0.713 |
| 10 | 169.40.42.231:443 | 286 ms | 10.5 ms | 15.82 Mbps | 2/2 | 0.708 |

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
