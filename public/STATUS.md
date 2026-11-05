# FreeWave VPN status

- Last run: 2026-11-05 20:06:16 UTC
- TCP candidates: 431
- Tested through Xray: 431
- Passed real HTTPS request through VLESS: 227
- Failed VLESS test: 204
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 227
- Successful 1024 KiB download tests: 186
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | ww13.levikogjgfdd.ir:23576 | 69 ms | 10.1 ms | 58.78 Mbps | 2/2 | 0.981 |
| 2 | 195.123.240.65:443 | 216 ms | 51.1 ms | 21.61 Mbps | 4/4 | 0.419 |
| 3 | 195.123.240.65:443 | 235 ms | 34.9 ms | 21.33 Mbps | 2/2 | 0.418 |
| 4 | 185.95.231.156:443 | 274 ms | 0.5 ms | 12.64 Mbps | new | 0.343 |
| 5 | 185.95.231.156:443 | 280 ms | 1.8 ms | 11.93 Mbps | 4/4 | 0.334 |
| 6 | 185.95.231.156:443 | 287 ms | 12.3 ms | 12.53 Mbps | 4/4 | 0.333 |
| 7 | 167.17.69.171:443 | 312 ms | 6.2 ms | 12.55 Mbps | 4/4 | 0.331 |
| 8 | 167.17.69.171:443 | 309 ms | 5.1 ms | 12.39 Mbps | 2/2 | 0.330 |
| 9 | 167.17.69.171:443 | 310 ms | 1.2 ms | 12.18 Mbps | new | 0.330 |
| 10 | 167.17.69.171:443 | 310 ms | 0.1 ms | 12.11 Mbps | new | 0.330 |

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
