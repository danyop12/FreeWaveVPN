# FreeWave VPN status

- Last run: 2026-11-08 06:12:09 UTC
- TCP candidates: 432
- Tested through Xray: 432
- Passed real HTTPS request through VLESS: 230
- Failed VLESS test: 202
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 230
- Successful 1024 KiB download tests: 171
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 105 ms | 4.0 ms | 14.98 Mbps | 4/4 | 0.911 |
| 2 | 195.123.240.65:443 | 122 ms | 1.9 ms | 14.65 Mbps | 3/3 | 0.868 |
| 3 | 195.211.98.43:443 | 231 ms | 7.4 ms | 15.54 Mbps | 4/4 | 0.802 |
| 4 | 167.17.69.171:443 | 314 ms | 11.0 ms | 12.52 Mbps | 3/3 | 0.667 |
| 5 | 167.17.69.171:443 | 311 ms | 5.6 ms | 12.41 Mbps | 3/3 | 0.666 |
| 6 | 167.17.69.171:443 | 313 ms | 1.8 ms | 12.36 Mbps | 4/4 | 0.665 |
| 7 | 167.17.69.171:443 | 319 ms | 3.5 ms | 12.32 Mbps | 2/2 | 0.662 |
| 8 | 167.17.69.171:443 | 316 ms | 0.9 ms | 12.25 Mbps | 4/4 | 0.661 |
| 9 | 185.95.231.156:443 | 287 ms | 1.1 ms | 12.03 Mbps | 5/5 | 0.661 |
| 10 | 167.17.69.171:443 | 311 ms | 2.9 ms | 12.23 Mbps | 4/4 | 0.661 |

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
