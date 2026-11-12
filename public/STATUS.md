# FreeWave VPN status

- Last run: 2026-11-12 20:05:30 UTC
- TCP candidates: 399
- Tested through Xray: 399
- Passed real HTTPS request through VLESS: 260
- Failed VLESS test: 139
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 260
- Successful 1024 KiB download tests: 192
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.206:23576 | 90 ms | 2.4 ms | 40.53 Mbps | 4/4 | 0.971 |
| 2 | 15.204.97.214:23576 | 97 ms | 7.0 ms | 40.74 Mbps | 3/3 | 0.947 |
| 3 | ww13.levikogjgfdd.ir:23576 | 102 ms | 13.0 ms | 39.41 Mbps | 2/2 | 0.909 |
| 4 | 195.123.240.65:443 | 134 ms | 26.8 ms | 40.89 Mbps | 3/3 | 0.863 |
| 5 | 195.123.240.65:443 | 146 ms | 63.5 ms | 39.75 Mbps | 3/4 | 0.761 |
| 6 | 195.123.240.65:443 | 274 ms | 102.9 ms | 38.20 Mbps | 4/5 | 0.679 |
| 7 | 162.159.198.1:443 | 84 ms | 0.0 ms | not measured | new | 0.450 |
| 8 | 185.95.231.156:443 | 278 ms | 3.1 ms | 12.62 Mbps | 4/4 | 0.409 |
| 9 | 195.211.98.43:443 | 606 ms | 25.2 ms | 16.19 Mbps | 3/3 | 0.403 |
| 10 | 167.17.69.171:443 | 290 ms | 2.4 ms | 12.22 Mbps | 3/3 | 0.400 |

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
