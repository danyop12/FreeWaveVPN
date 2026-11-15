# FreeWave VPN status

- Last run: 2026-11-15 19:01:13 UTC
- TCP candidates: 373
- Tested through Xray: 373
- Passed real HTTPS request through VLESS: 206
- Failed VLESS test: 167
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 206
- Successful 1024 KiB download tests: 178
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 167.17.69.171:443 | 66 ms | 1.3 ms | 56.11 Mbps | 4/4 | 0.935 |
| 2 | 167.17.69.171:443 | 65 ms | 0.9 ms | 53.66 Mbps | 2/2 | 0.917 |
| 3 | 167.17.69.171:443 | 68 ms | 2.7 ms | 52.76 Mbps | 6/6 | 0.892 |
| 4 | 167.17.69.171:443 | 64 ms | 0.7 ms | 50.45 Mbps | 3/3 | 0.889 |
| 5 | 169.40.42.231:443 | 80 ms | 7.0 ms | 55.46 Mbps | 5/5 | 0.876 |
| 6 | 167.17.69.171:443 | 70 ms | 5.8 ms | 49.75 Mbps | 4/4 | 0.851 |
| 7 | 167.17.69.171:443 | 66 ms | 2.6 ms | 46.13 Mbps | 5/5 | 0.836 |
| 8 | 167.17.69.171:443 | 64 ms | 3.3 ms | 44.85 Mbps | 4/4 | 0.830 |
| 9 | 169.40.42.90:443 | 135 ms | 3.8 ms | 55.74 Mbps | 4/4 | 0.804 |
| 10 | 169.40.42.168:443 | 232 ms | 1.1 ms | 56.99 Mbps | 4/4 | 0.770 |

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
