# FreeWave VPN status

- Last run: 2026-11-22 19:50:55 UTC
- TCP candidates: 366
- Tested through Xray: 366
- Passed real HTTPS request through VLESS: 176
- Failed VLESS test: 190
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 176
- Successful 1024 KiB download tests: 160
- Nodes geolocated to Albania (AL): 1

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | ww13.levikogjgfdd.ir:23576 | 147 ms | 4.8 ms | 24.64 Mbps | 4/4 | 0.994 |
| 2 | ww13.levikogjgfdd.ir:23576 | 155 ms | 10.9 ms | 24.71 Mbps | 3/3 | 0.973 |
| 3 | 185.95.231.156:443 | 210 ms | 2.8 ms | 16.70 Mbps | 5/5 | 0.730 |
| 4 | 185.95.231.156:443 | 221 ms | 4.6 ms | 16.73 Mbps | 4/4 | 0.719 |
| 5 | 195.123.240.65:443 | 203 ms | 4.1 ms | 18.10 Mbps | 2/3 | 0.702 |
| 6 | 185.95.231.156:443 | 224 ms | 8.0 ms | 16.19 Mbps | 3/3 | 0.701 |
| 7 | 185.95.231.156:443 | 254 ms | 37.2 ms | 16.81 Mbps | 2/2 | 0.678 |
| 8 | 169.40.42.231:443 | 284 ms | 11.0 ms | 16.81 Mbps | 5/5 | 0.674 |
| 9 | 169.40.42.229:443 | 279 ms | 3.0 ms | 16.30 Mbps | 2/2 | 0.669 |
| 10 | 169.40.42.202:443 | 241 ms | 16.8 ms | 14.79 Mbps | 5/5 | 0.652 |

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
