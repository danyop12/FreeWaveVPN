# FreeWave VPN status

- Last run: 2026-11-24 22:35:39 UTC
- TCP candidates: 398
- Tested through Xray: 398
- Passed real HTTPS request through VLESS: 193
- Failed VLESS test: 205
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 193
- Successful 1024 KiB download tests: 171
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 148.135.86.162:10000 | 781 ms | 0.2 ms | 64.24 Mbps | new | 0.742 |
| 2 | ww13.levikogjgfdd.ir:23576 | 156 ms | 11.4 ms | 24.13 Mbps | 6/6 | 0.554 |
| 3 | 15.204.97.206:23576 | 146 ms | 0.4 ms | 20.48 Mbps | 2/2 | 0.547 |
| 4 | 172.64.154.113:8443 | 108 ms | 24.8 ms | not measured | new | 0.422 |
| 5 | 172.64.154.113:8443 | 120 ms | 0.9 ms | not measured | 2/2 | 0.418 |
| 6 | 166.0.25.92:443 | 137 ms | 11.7 ms | 4.20 Mbps | new | 0.411 |
| 7 | 195.211.98.43:443 | 248 ms | 7.8 ms | 14.89 Mbps | 2/2 | 0.404 |
| 8 | 185.95.231.156:443 | 250 ms | 2.4 ms | 12.06 Mbps | 3/3 | 0.382 |
| 9 | 172.64.154.113:8443 | 135 ms | 8.3 ms | not measured | new | 0.381 |
| 10 | 195.123.235.183:443 | 296 ms | 1.3 ms | 11.79 Mbps | new | 0.360 |

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
