# FreeWave VPN status

- Last run: 2026-11-14 06:34:28 UTC
- TCP candidates: 424
- Tested through Xray: 424
- Passed real HTTPS request through VLESS: 217
- Failed VLESS test: 207
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 217
- Successful 1024 KiB download tests: 138
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 167.17.69.171:443 | 64 ms | 3.8 ms | 52.72 Mbps | 6/6 | 0.838 |
| 2 | 167.17.69.171:443 | 62 ms | 1.9 ms | 50.79 Mbps | 2/2 | 0.834 |
| 3 | 167.17.69.171:443 | 70 ms | 6.4 ms | 53.30 Mbps | 3/3 | 0.820 |
| 4 | 169.40.42.229:443 | 99 ms | 36.3 ms | 61.70 Mbps | 3/3 | 0.806 |
| 5 | 167.17.69.171:443 | 82 ms | 4.2 ms | 53.22 Mbps | 3/3 | 0.794 |
| 6 | 169.40.42.202:443 | 97 ms | 27.1 ms | 58.01 Mbps | 3/3 | 0.785 |
| 7 | 167.17.69.171:443 | 63 ms | 0.1 ms | 44.71 Mbps | 5/5 | 0.779 |
| 8 | 167.17.69.171:443 | 80 ms | 12.1 ms | 51.86 Mbps | 3/3 | 0.773 |
| 9 | 169.40.42.89:443 | 123 ms | 18.9 ms | 58.39 Mbps | 2/2 | 0.769 |
| 10 | 169.40.42.168:443 | 70 ms | 1.2 ms | 43.09 Mbps | 2/2 | 0.740 |

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
