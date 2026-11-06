# FreeWave VPN status

- Last run: 2026-11-06 19:32:54 UTC
- TCP candidates: 414
- Tested through Xray: 414
- Passed real HTTPS request through VLESS: 192
- Failed VLESS test: 222
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 192
- Successful 1024 KiB download tests: 171
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 101 ms | 3.8 ms | 40.82 Mbps | 5/5 | 0.963 |
| 2 | 195.123.240.65:443 | 107 ms | 27.9 ms | 43.29 Mbps | 1/2 | 0.835 |
| 3 | 15.204.97.214:23576 | 156 ms | 2.6 ms | 24.12 Mbps | 2/2 | 0.648 |
| 4 | 15.204.97.214:23576 | 155 ms | 5.3 ms | 23.77 Mbps | new | 0.643 |
| 5 | 195.211.98.43:443 | 245 ms | 7.6 ms | 14.67 Mbps | 5/5 | 0.456 |
| 6 | 159.89.87.21:28190 | 254 ms | 4.3 ms | 14.20 Mbps | new | 0.448 |
| 7 | 185.95.231.156:443 | 255 ms | 4.8 ms | 14.21 Mbps | 4/4 | 0.447 |
| 8 | 159.89.87.21:28190 | 249 ms | 3.5 ms | 13.83 Mbps | 5/5 | 0.446 |
| 9 | 159.89.87.21:28190 | 257 ms | 10.2 ms | 14.36 Mbps | 4/4 | 0.445 |
| 10 | 159.89.87.21:28190 | 257 ms | 6.1 ms | 13.99 Mbps | 2/2 | 0.443 |

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
