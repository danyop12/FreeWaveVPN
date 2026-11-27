# FreeWave VPN status

- Last run: 2026-11-27 21:25:27 UTC
- TCP candidates: 391
- Tested through Xray: 391
- Passed real HTTPS request through VLESS: 169
- Failed VLESS test: 222
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 169
- Successful 1024 KiB download tests: 135
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 169.40.42.202:443 | 90 ms | 6.0 ms | 43.48 Mbps | 2/2 | 0.872 |
| 2 | 185.95.231.156:443 | 74 ms | 1.3 ms | 38.53 Mbps | 4/4 | 0.866 |
| 3 | 159.89.87.21:28190 | 70 ms | 3.3 ms | 37.45 Mbps | 3/3 | 0.861 |
| 4 | 159.89.87.21:28190 | 72 ms | 1.5 ms | 37.69 Mbps | 4/4 | 0.860 |
| 5 | 159.89.87.21:28190 | 81 ms | 6.6 ms | 38.38 Mbps | 3/3 | 0.831 |
| 6 | 159.89.87.21:28190 | 91 ms | 1.0 ms | 39.25 Mbps | 5/5 | 0.827 |
| 7 | 169.40.42.202:443 | 216 ms | 45.1 ms | 45.77 Mbps | 3/3 | 0.761 |
| 8 | 169.40.42.35:443 | 225 ms | 21.1 ms | 44.10 Mbps | 4/4 | 0.750 |
| 9 | 169.40.42.89:443 | 127 ms | 14.9 ms | 37.40 Mbps | 4/4 | 0.732 |
| 10 | 169.40.42.35:443 | 103 ms | 18.0 ms | 32.70 Mbps | 3/3 | 0.703 |

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
