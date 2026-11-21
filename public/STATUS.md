# FreeWave VPN status

- Last run: 2026-11-21 07:19:15 UTC
- TCP candidates: 402
- Tested through Xray: 402
- Passed real HTTPS request through VLESS: 118
- Failed VLESS test: 284
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 118
- Successful 1024 KiB download tests: 107
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 149 ms | 6.6 ms | 23.78 Mbps | 2/2 | 0.980 |
| 2 | 185.95.231.156:443 | 154 ms | 18.1 ms | 22.84 Mbps | 4/4 | 0.939 |
| 3 | 169.40.42.168:443 | 233 ms | 12.0 ms | 24.40 Mbps | 5/5 | 0.885 |
| 4 | 167.17.69.171:443 | 210 ms | 1.3 ms | 20.05 Mbps | 2/2 | 0.815 |
| 5 | 169.40.42.90:443 | 297 ms | 12.3 ms | 22.58 Mbps | 3/3 | 0.804 |
| 6 | 169.40.42.163:443 | 217 ms | 54.4 ms | 20.91 Mbps | 3/3 | 0.797 |
| 7 | 167.17.69.171:443 | 207 ms | 13.5 ms | 19.51 Mbps | new | 0.797 |
| 8 | 167.17.69.171:443 | 215 ms | 23.0 ms | 20.08 Mbps | 2/2 | 0.796 |
| 9 | 169.40.42.202:443 | 166 ms | 7.1 ms | 16.31 Mbps | 4/4 | 0.782 |
| 10 | 169.40.42.90:443 | 247 ms | 19.5 ms | 19.91 Mbps | 5/5 | 0.769 |

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
