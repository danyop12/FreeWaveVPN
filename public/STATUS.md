# FreeWave VPN status

- Last run: 2026-11-05 00:45:31 UTC
- TCP candidates: 429
- Tested through Xray: 429
- Passed real HTTPS request through VLESS: 262
- Failed VLESS test: 167
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 262
- Successful 1024 KiB download tests: 207
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.211.98.43:443 | 212 ms | 86.5 ms | 37.82 Mbps | new | 0.764 |
| 2 | 159.89.87.21:28190 | 179 ms | 20.7 ms | 24.83 Mbps | new | 0.623 |
| 3 | 167.17.69.171:443 | 200 ms | 6.8 ms | 23.16 Mbps | new | 0.596 |
| 4 | 167.17.69.171:443 | 195 ms | 2.5 ms | 22.25 Mbps | new | 0.589 |
| 5 | 195.123.235.183:443 | 164 ms | 1.6 ms | 20.20 Mbps | new | 0.582 |
| 6 | 169.40.42.229:443 | 204 ms | 0.2 ms | 21.80 Mbps | new | 0.579 |
| 7 | 195.123.235.183:443 | 157 ms | 7.0 ms | 19.65 Mbps | new | 0.575 |
| 8 | 169.40.42.74:443 | 237 ms | 28.3 ms | 23.62 Mbps | new | 0.574 |
| 9 | 169.40.42.202:443 | 165 ms | 8.4 ms | 19.78 Mbps | new | 0.569 |
| 10 | 159.89.87.21:28190 | 182 ms | 3.1 ms | 20.28 Mbps | new | 0.568 |

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
