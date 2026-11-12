# FreeWave VPN status

- Last run: 2026-11-12 07:22:50 UTC
- TCP candidates: 388
- Tested through Xray: 388
- Passed real HTTPS request through VLESS: 239
- Failed VLESS test: 149
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 239
- Successful 1024 KiB download tests: 198
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.206:23576 | 146 ms | 1.0 ms | 24.24 Mbps | 3/3 | 0.999 |
| 2 | 195.211.98.43:443 | 155 ms | 1.6 ms | 22.72 Mbps | 4/4 | 0.947 |
| 3 | 195.211.98.43:443 | 256 ms | 88.2 ms | 23.26 Mbps | 3/3 | 0.810 |
| 4 | 185.95.231.156:443 | 193 ms | 2.6 ms | 18.62 Mbps | 2/2 | 0.797 |
| 5 | 185.95.231.156:443 | 193 ms | 1.0 ms | 18.50 Mbps | 2/2 | 0.796 |
| 6 | 185.95.231.156:443 | 189 ms | 0.9 ms | 17.99 Mbps | 3/3 | 0.789 |
| 7 | 169.40.42.90:443 | 219 ms | 2.8 ms | 16.55 Mbps | 4/4 | 0.724 |
| 8 | 167.17.69.171:443 | 224 ms | 2.6 ms | 16.36 Mbps | 3/3 | 0.715 |
| 9 | 167.17.69.171:443 | 219 ms | 3.1 ms | 16.11 Mbps | 3/3 | 0.713 |
| 10 | 167.17.69.171:443 | 217 ms | 0.3 ms | 15.91 Mbps | 4/4 | 0.712 |

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
