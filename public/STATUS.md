# FreeWave VPN status

- Last run: 2026-11-13 06:56:35 UTC
- TCP candidates: 416
- Tested through Xray: 416
- Passed real HTTPS request through VLESS: 250
- Failed VLESS test: 166
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 250
- Successful 1024 KiB download tests: 170
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 95 ms | 8.5 ms | 44.09 Mbps | 2/2 | 0.943 |
| 2 | ww13.levikogjgfdd.ir:23576 | 89 ms | 0.7 ms | 40.79 Mbps | 3/3 | 0.931 |
| 3 | 15.204.97.214:23576 | 91 ms | 2.5 ms | 40.50 Mbps | 3/3 | 0.918 |
| 4 | 195.123.240.65:443 | 134 ms | 10.3 ms | 38.84 Mbps | 4/5 | 0.765 |
| 5 | 172.67.199.126:2053 | 425 ms | 129.5 ms | 25.82 Mbps | new | 0.494 |
| 6 | 104.24.196.20:443 | 81 ms | 1.9 ms | not measured | new | 0.447 |
| 7 | 104.18.39.33:443 | 84 ms | 0.0 ms | not measured | new | 0.440 |
| 8 | 172.67.74.177:443 | 89 ms | 2.5 ms | not measured | new | 0.418 |
| 9 | 162.159.198.1:443 | 89 ms | 3.2 ms | not measured | new | 0.417 |
| 10 | 104.26.13.54:443 | 88 ms | 6.6 ms | not measured | new | 0.416 |

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
