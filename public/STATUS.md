# FreeWave VPN status

- Last run: 2026-11-07 13:12:00 UTC
- TCP candidates: 407
- Tested through Xray: 407
- Passed real HTTPS request through VLESS: 224
- Failed VLESS test: 183
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 224
- Successful 1024 KiB download tests: 186
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 108 ms | 1.2 ms | 34.19 Mbps | 2/2 | 0.998 |
| 2 | 185.95.231.156:443 | 120 ms | 15.5 ms | 32.37 Mbps | 6/6 | 0.924 |
| 3 | 167.17.69.171:443 | 123 ms | 8.7 ms | 32.27 Mbps | 6/6 | 0.923 |
| 4 | 167.17.69.171:443 | 117 ms | 2.7 ms | 30.91 Mbps | new | 0.922 |
| 5 | 185.95.231.156:443 | 118 ms | 3.0 ms | 30.18 Mbps | 3/3 | 0.907 |
| 6 | 167.17.69.171:443 | 130 ms | 5.3 ms | 31.90 Mbps | 2/2 | 0.906 |
| 7 | 167.17.69.171:443 | 139 ms | 8.1 ms | 32.45 Mbps | 3/3 | 0.897 |
| 8 | 195.123.235.183:443 | 120 ms | 3.6 ms | 28.61 Mbps | 4/4 | 0.876 |
| 9 | 167.17.69.171:443 | 125 ms | 8.9 ms | 29.34 Mbps | 5/5 | 0.872 |
| 10 | 167.17.69.171:443 | 129 ms | 3.6 ms | 29.33 Mbps | new | 0.869 |

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
