# FreeWave VPN status

- Last run: 2026-11-04 22:01:12 UTC
- TCP candidates: 424
- Tested through Xray: 160
- Passed real HTTPS request through VLESS: 11
- Failed VLESS test: 149
- Not tested due to limit: 264
- Successful 256 KiB download tests: 11

## Top nodes from this GitHub Actions run

| # | Server | Latency | Download test | Score |
|---:|---|---:|---:|---:|
| 1 | book.jarbook.net:443 | 693 ms | 2.22 Mbps | 0.920 |
| 2 | 51.158.206.25:23576 | 534 ms | 1.95 Mbps | 0.920 |
| 3 | 51.158.206.97:23576 | 553 ms | 1.65 Mbps | 0.819 |
| 4 | direct-nl.bachidze.com:443 | 650 ms | 1.65 Mbps | 0.771 |
| 5 | 144.31.4.234:443 | 658 ms | 1.63 Mbps | 0.761 |
| 6 | api.ferrin.uk:443 | 820 ms | 1.78 Mbps | 0.748 |
| 7 | 79.141.164.81:443 | 875 ms | 1.66 Mbps | 0.700 |
| 8 | 79.141.164.125:443 | 728 ms | 1.50 Mbps | 0.696 |
| 9 | api.ferrin.uk:443 | 843 ms | 1.60 Mbps | 0.690 |
| 10 | 150.40.126.21:443 | 970 ms | 1.67 Mbps | 0.679 |

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
