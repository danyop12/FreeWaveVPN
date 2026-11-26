# FreeWave VPN status

- Last run: 2026-11-26 08:30:04 UTC
- TCP candidates: 405
- Tested through Xray: 405
- Passed real HTTPS request through VLESS: 130
- Failed VLESS test: 275
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 130
- Successful 1024 KiB download tests: 100
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 159.89.87.21:28190 | 50 ms | 4.9 ms | 56.60 Mbps | 5/5 | 0.923 |
| 2 | 169.40.42.229:443 | 67 ms | 0.8 ms | 54.94 Mbps | 3/3 | 0.854 |
| 3 | 169.40.42.90:443 | 79 ms | 7.8 ms | 58.20 Mbps | 4/4 | 0.845 |
| 4 | 169.40.42.90:443 | 162 ms | 29.0 ms | 57.30 Mbps | 5/5 | 0.746 |
| 5 | 185.95.231.156:443 | 107 ms | 4.7 ms | 51.33 Mbps | 4/4 | 0.746 |
| 6 | 169.40.42.35:443 | 134 ms | 16.7 ms | 54.76 Mbps | 4/4 | 0.744 |
| 7 | 169.40.42.89:443 | 67 ms | 0.5 ms | 40.10 Mbps | 5/5 | 0.715 |
| 8 | 169.40.42.89:443 | 125 ms | 52.5 ms | 52.62 Mbps | 4/4 | 0.703 |
| 9 | 185.95.231.156:443 | 103 ms | 3.9 ms | 43.45 Mbps | 4/4 | 0.677 |
| 10 | 169.40.42.90:443 | 123 ms | 13.2 ms | 44.35 Mbps | 3/3 | 0.656 |

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
