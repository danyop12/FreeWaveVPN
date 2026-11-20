# FreeWave VPN status

- Last run: 2026-11-20 07:29:27 UTC
- TCP candidates: 396
- Tested through Xray: 396
- Passed real HTTPS request through VLESS: 183
- Failed VLESS test: 213
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 183
- Successful 1024 KiB download tests: 160
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 284 ms | 1.6 ms | 12.23 Mbps | 3/3 | 0.904 |
| 2 | 169.40.42.35:443 | 306 ms | 7.5 ms | 11.86 Mbps | 3/3 | 0.870 |
| 3 | 167.17.69.171:443 | 317 ms | 16.9 ms | 11.54 Mbps | 3/3 | 0.845 |
| 4 | 167.17.69.171:443 | 334 ms | 17.7 ms | 11.45 Mbps | 2/2 | 0.832 |
| 5 | 209.200.246.148:443 | 328 ms | 11.8 ms | 11.19 Mbps | 2/2 | 0.826 |
| 6 | 169.40.42.35:443 | 406 ms | 16.1 ms | 11.94 Mbps | 2/2 | 0.825 |
| 7 | 169.40.42.229:443 | 392 ms | 22.2 ms | 11.85 Mbps | 3/3 | 0.824 |
| 8 | 169.40.42.168:443 | 293 ms | 1.8 ms | 10.29 Mbps | 4/4 | 0.811 |
| 9 | 167.17.69.171:443 | 331 ms | 24.1 ms | 10.96 Mbps | 3/3 | 0.809 |
| 10 | 169.40.42.168:443 | 354 ms | 44.5 ms | 11.36 Mbps | 3/3 | 0.809 |

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
