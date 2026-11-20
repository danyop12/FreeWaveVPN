# FreeWave VPN status

- Last run: 2026-11-20 20:33:27 UTC
- TCP candidates: 389
- Tested through Xray: 389
- Passed real HTTPS request through VLESS: 166
- Failed VLESS test: 223
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 166
- Successful 1024 KiB download tests: 135
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.206:23576 | 51 ms | 3.3 ms | 71.50 Mbps | 2/2 | 0.991 |
| 2 | 167.17.69.171:443 | 306 ms | 1.2 ms | 11.05 Mbps | 4/4 | 0.284 |
| 3 | 167.17.69.171:443 | 305 ms | 4.1 ms | 11.06 Mbps | 4/4 | 0.283 |
| 4 | 167.17.69.171:443 | 304 ms | 3.6 ms | 11.02 Mbps | 4/4 | 0.283 |
| 5 | 167.17.69.171:443 | 304 ms | 0.1 ms | 10.68 Mbps | 3/3 | 0.282 |
| 6 | 167.17.69.171:443 | 316 ms | 5.3 ms | 11.12 Mbps | 4/4 | 0.281 |
| 7 | 169.40.42.231:443 | 344 ms | 4.8 ms | 11.43 Mbps | 5/5 | 0.280 |
| 8 | 167.17.69.171:443 | 311 ms | 9.2 ms | 11.01 Mbps | 4/4 | 0.279 |
| 9 | 167.17.69.171:443 | 312 ms | 6.2 ms | 10.62 Mbps | 3/3 | 0.278 |
| 10 | 167.17.69.171:443 | 319 ms | 14.9 ms | 11.10 Mbps | 2/2 | 0.276 |

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
