# FreeWave VPN status

- Last run: 2026-11-04 22:12:51 UTC
- TCP candidates: 424
- Tested through Xray: 424
- Passed real HTTPS request through VLESS: 20
- Failed VLESS test: 404
- Not tested due to limit: 0
- Nodes confirmed by 2 separate HTTPS probes: 20
- Successful 256 KiB download tests: 20

## Top nodes from this GitHub Actions run

| # | Server | Latency | Download test | Score |
|---:|---|---:|---:|---:|
| 1 | 79.141.164.83:443 | 322 ms | 3.41 Mbps | 1.000 |
| 2 | 79.141.164.83:443 | 332 ms | 3.36 Mbps | 0.981 |
| 3 | 51.158.206.97:23576 | 370 ms | 2.72 Mbps | 0.823 |
| 4 | snow.betust.net:443 | 374 ms | 2.66 Mbps | 0.809 |
| 5 | 51.158.206.25:23576 | 380 ms | 2.42 Mbps | 0.758 |
| 6 | book.jarbook.net:443 | 334 ms | 2.02 Mbps | 0.722 |
| 7 | api.ferrin.uk:443 | 569 ms | 2.70 Mbps | 0.712 |
| 8 | 217.30.10.72:443 | 466 ms | 2.39 Mbps | 0.697 |
| 9 | 150.40.126.21:443 | 493 ms | 2.42 Mbps | 0.690 |
| 10 | direct-nl.bachidze.com:443 | 452 ms | 2.20 Mbps | 0.668 |

## Subscription files

- freewave-vless.txt — nodes passing two independent HTTPS probes through Xray
- freewave-best.txt — the single highest-ranked VLESS key for clients that import one key
- freewave-best-base64.txt — Base64 version of the single best key
- freewave-fast.txt — top 25 by relative score
- freewave-fast-base64.txt — Base64 version of the top 25
- freewave-vless-base64.txt — Base64 version of validated nodes
- freewave-tcp.txt — preliminary TCP-only candidates
- metrics.json — detailed test results

> Measurements are from GitHub Actions, not from your phone. Your ISP,
> country, route, time of day, and client settings can change the result.
> The small download test is a snapshot, not a sustained-speed or unlimited-traffic guarantee.
> Two successful HTTPS probes verify two HTTPS destinations through the VLESS tunnel at test time;
> they do not prove all sites/apps, UDP support, privacy, or future uptime.
> Download rate uses curl's measured average transfer rate, not first-byte latency.
> Public nodes are third-party infrastructure and may log metadata or disappear.
