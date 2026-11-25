# FreeWave VPN status

- Last run: 2026-11-25 21:49:58 UTC
- TCP candidates: 412
- Tested through Xray: 412
- Passed real HTTPS request through VLESS: 188
- Failed VLESS test: 224
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 188
- Successful 1024 KiB download tests: 159
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 159.89.87.21:28190 | 69 ms | 1.0 ms | 50.24 Mbps | 3/3 | 0.936 |
| 2 | 159.89.87.21:28190 | 83 ms | 5.0 ms | 56.17 Mbps | 5/5 | 0.936 |
| 3 | 195.123.235.183:443 | 79 ms | 0.5 ms | 43.35 Mbps | 2/2 | 0.833 |
| 4 | 169.40.42.35:443 | 94 ms | 7.2 ms | 46.36 Mbps | 5/5 | 0.810 |
| 5 | 169.40.42.35:443 | 92 ms | 10.8 ms | 43.18 Mbps | 3/3 | 0.779 |
| 6 | 185.95.231.156:443 | 104 ms | 18.8 ms | 46.55 Mbps | 5/5 | 0.778 |
| 7 | 195.123.235.183:443 | 91 ms | 0.2 ms | 36.71 Mbps | new | 0.734 |
| 8 | 169.40.42.231:443 | 90 ms | 2.8 ms | 34.38 Mbps | 2/2 | 0.709 |
| 9 | 169.40.42.74:443 | 90 ms | 1.9 ms | 34.08 Mbps | 3/3 | 0.707 |
| 10 | 169.40.42.231:443 | 178 ms | 13.1 ms | 44.73 Mbps | new | 0.692 |

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
