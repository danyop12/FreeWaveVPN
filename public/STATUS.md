# FreeWave VPN status

- Last run: 2026-11-04 21:59:04 UTC
- TCP candidates: 424
- Tested through Xray: 80
- Passed real HTTPS request through VLESS: 8
- Failed VLESS test: 72
- Not tested due to limit: 344
- Successful 256 KiB download tests: 7

## Top nodes from this GitHub Actions run

| # | Server | Latency | Download test | Score |
|---:|---|---:|---:|---:|
| 1 | 51.158.206.97:23576 | 348 ms | 2.84 Mbps | 1.000 |
| 2 | api.ferrin.uk:443 | 602 ms | 2.64 Mbps | 0.806 |
| 3 | 144.31.4.234:443 | 442 ms | 2.27 Mbps | 0.796 |
| 4 | api.ferrin.uk:443 | 601 ms | 2.52 Mbps | 0.778 |
| 5 | 79.141.164.125:443 | 467 ms | 2.02 Mbps | 0.724 |
| 6 | 79.141.164.81:443 | 525 ms | 2.05 Mbps | 0.700 |
| 7 | apply.betust.net:443 | 619 ms | 1.79 Mbps | 0.606 |
| 8 | 185.126.93.225:443 | 785 ms | not measured | 0.155 |

## Subscription files

- freewave-vless.txt — only nodes passing a real HTTPS request through Xray
- freewave-fast.txt — top 25 by relative score
- freewave-fast-base64.txt — Base64 version of the top 25
- freewave-vless-base64.txt — Base64 version of validated nodes
- freewave-tcp.txt — preliminary TCP-only candidates
- metrics.json — detailed test results

> Measurements are from GitHub Actions, not from your phone. Your ISP,
> country, route, time of day, and client settings can change the result.
> The small download test is a snapshot, not a sustained-speed or unlimited-traffic guarantee.
> A successful HTTPS request verifies one VLESS route to the probe target at test time;
> it does not prove all sites/apps, UDP support, privacy, or future uptime.
> Public nodes are third-party infrastructure and may log metadata or disappear.
