# FreeWave VPN status

- Last run: 2026-11-05 00:53:57 UTC
- TCP candidates: 427
- Tested through Xray: 427
- Passed real HTTPS request through VLESS: 261
- Failed VLESS test: 166
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 261
- Successful 1024 KiB download tests: 214
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 178 ms | 6.7 ms | 21.35 Mbps | new | 0.904 |
| 2 | 159.89.87.21:28190 | 179 ms | 1.8 ms | 20.03 Mbps | 2/2 | 0.872 |
| 3 | 195.123.235.183:443 | 187 ms | 7.2 ms | 19.86 Mbps | 2/2 | 0.855 |
| 4 | 159.89.87.21:28190 | 190 ms | 10.0 ms | 19.64 Mbps | 2/2 | 0.844 |
| 5 | 159.89.87.21:28190 | 186 ms | 3.2 ms | 18.79 Mbps | 2/2 | 0.831 |
| 6 | 159.89.87.21:28190 | 194 ms | 4.0 ms | 19.06 Mbps | 2/2 | 0.830 |
| 7 | 108.61.119.63:18580 | 181 ms | 4.2 ms | 18.48 Mbps | 2/2 | 0.829 |
| 8 | 195.123.235.183:443 | 187 ms | 1.1 ms | 18.54 Mbps | 2/2 | 0.826 |
| 9 | 195.123.235.183:443 | 199 ms | 1.1 ms | 18.38 Mbps | 2/2 | 0.810 |
| 10 | 195.123.235.183:443 | 198 ms | 8.9 ms | 18.32 Mbps | 2/2 | 0.803 |

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
