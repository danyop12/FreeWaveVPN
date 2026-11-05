# FreeWave VPN status

- Last run: 2026-11-05 00:31:37 UTC
- TCP candidates: 424
- Tested through Xray: 424
- Passed real HTTPS request through VLESS: 28
- Failed VLESS test: 396
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 28
- Successful 1024 KiB download tests: 21
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 79.141.164.83:443 | 284 ms | 57.3 ms | 9.02 Mbps | 4/4 | 0.885 |
| 2 | 79.141.164.125:443 | 286 ms | 51.2 ms | 8.86 Mbps | 4/4 | 0.876 |
| 3 | 79.141.164.83:443 | 291 ms | 59.6 ms | 8.66 Mbps | 4/4 | 0.857 |
| 4 | api.ferrin.uk:443 | 696 ms | 34.3 ms | 9.28 Mbps | 4/4 | 0.785 |
| 5 | 51.158.206.97:23576 | 445 ms | 4.1 ms | 7.54 Mbps | 4/4 | 0.739 |
| 6 | book.jarbook.net:443 | 620 ms | 22.3 ms | 8.07 Mbps | 4/4 | 0.726 |
| 7 | 51.158.206.25:23576 | 478 ms | 7.1 ms | 7.39 Mbps | 4/4 | 0.720 |
| 8 | 89.110.64.15:443 | 454 ms | 16.5 ms | 7.12 Mbps | 4/4 | 0.708 |
| 9 | 217.30.10.72:443 | 522 ms | 7.5 ms | 7.30 Mbps | 4/4 | 0.703 |
| 10 | api.ferrin.uk:443 | 668 ms | 26.8 ms | 7.51 Mbps | 4/4 | 0.685 |

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
