# FreeWave VPN status

- Last run: 2026-11-04 23:42:47 UTC
- TCP candidates: 424
- Tested through Xray: 424
- Passed real HTTPS request through VLESS: 20
- Failed VLESS test: 404
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 20
- Successful 1024 KiB download tests: 20

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 79.141.164.83:443 | 257 ms | 54.9 ms | 9.65 Mbps | new | 0.949 |
| 2 | 79.141.164.83:443 | 266 ms | 56.9 ms | 9.64 Mbps | new | 0.938 |
| 3 | book.jarbook.net:443 | 274 ms | 49.0 ms | 8.58 Mbps | new | 0.875 |
| 4 | api.ferrin.uk:443 | 586 ms | 6.6 ms | 10.11 Mbps | new | 0.830 |
| 5 | api.ferrin.uk:443 | 604 ms | 22.5 ms | 9.19 Mbps | new | 0.772 |
| 6 | 51.158.206.97:23576 | 443 ms | 4.2 ms | 7.94 Mbps | new | 0.754 |
| 7 | 51.158.206.25:23576 | 419 ms | 5.8 ms | 7.70 Mbps | new | 0.751 |
| 8 | 217.30.10.72:443 | 514 ms | 11.3 ms | 7.50 Mbps | new | 0.705 |
| 9 | direct-nl.bachidze.com:443 | 507 ms | 22.6 ms | 6.78 Mbps | new | 0.664 |
| 10 | 89.110.64.15:443 | 444 ms | 11.6 ms | 6.06 Mbps | new | 0.649 |

## Subscription files

- freewave-vless.txt — nodes passing two independent HTTPS probes through Xray
- freewave-best.txt — the single highest-ranked VLESS key for clients that import one key
- freewave-best-base64.txt — Base64 version of the single best key
- freewave-fast.txt — top 25 by relative score
- freewave-fast-base64.txt — Base64 version of the top 25
- freewave-vless-base64.txt — Base64 version of validated nodes
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
