# FreeWave VPN status

- Last run: 2026-11-09 22:32:33 UTC
- TCP candidates: 403
- Tested through Xray: 403
- Passed real HTTPS request through VLESS: 279
- Failed VLESS test: 124
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 279
- Successful 1024 KiB download tests: 218
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 106 ms | 4.5 ms | 50.08 Mbps | 4/5 | 0.940 |
| 2 | 195.123.240.65:443 | 215 ms | 141.9 ms | 50.49 Mbps | 4/4 | 0.788 |
| 3 | 47.251.108.158:443 | 145 ms | 5.4 ms | 35.23 Mbps | 2/3 | 0.685 |
| 4 | 15.204.97.214:23576 | 146 ms | 1.4 ms | 24.13 Mbps | 3/3 | 0.629 |
| 5 | 159.89.87.21:28190 | 258 ms | 5.1 ms | 13.99 Mbps | 2/2 | 0.423 |
| 6 | 159.89.87.21:28190 | 255 ms | 0.4 ms | 13.31 Mbps | 3/3 | 0.419 |
| 7 | 159.89.87.21:28190 | 256 ms | 2.0 ms | 13.20 Mbps | 6/6 | 0.417 |
| 8 | 159.89.87.21:28190 | 263 ms | 2.6 ms | 13.04 Mbps | 4/4 | 0.411 |
| 9 | 185.95.231.156:443 | 282 ms | 4.0 ms | 13.24 Mbps | 4/4 | 0.405 |
| 10 | 159.89.87.21:28190 | 269 ms | 11.4 ms | 13.01 Mbps | 4/4 | 0.404 |

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
