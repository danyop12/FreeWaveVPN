# FreeWave VPN status

- Last run: 2026-11-04 23:50:42 UTC
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
| 1 | 79.141.164.83:443 | 283 ms | 57.0 ms | 8.66 Mbps | 2/2 | 0.971 |
| 2 | 79.141.164.83:443 | 293 ms | 57.4 ms | 8.69 Mbps | 2/2 | 0.962 |
| 3 | book.jarbook.net:443 | 298 ms | 57.3 ms | 7.38 Mbps | 2/2 | 0.876 |
| 4 | api.ferrin.uk:443 | 730 ms | 50.6 ms | 8.73 Mbps | 2/2 | 0.807 |
| 5 | api.ferrin.uk:443 | 737 ms | 3.9 ms | 8.37 Mbps | 2/2 | 0.792 |
| 6 | 51.158.206.97:23576 | 482 ms | 5.2 ms | 7.39 Mbps | 2/2 | 0.790 |
| 7 | 89.110.64.15:443 | 474 ms | 18.3 ms | 7.37 Mbps | 2/2 | 0.788 |
| 8 | 51.158.206.25:23576 | 462 ms | 3.0 ms | 6.98 Mbps | 2/2 | 0.773 |
| 9 | 217.30.10.72:443 | 540 ms | 6.6 ms | 6.86 Mbps | 2/2 | 0.738 |
| 10 | 79.141.164.125:443 | 577 ms | 170.9 ms | 7.39 Mbps | 2/2 | 0.729 |

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
