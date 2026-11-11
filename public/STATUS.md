# FreeWave VPN status

- Last run: 2026-11-11 06:35:49 UTC
- TCP candidates: 420
- Tested through Xray: 420
- Passed real HTTPS request through VLESS: 243
- Failed VLESS test: 177
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 243
- Successful 1024 KiB download tests: 206
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 144 ms | 13.7 ms | 26.16 Mbps | 4/4 | 0.954 |
| 2 | 159.89.87.21:28190 | 153 ms | 6.5 ms | 26.24 Mbps | 2/2 | 0.947 |
| 3 | 159.89.87.21:28190 | 156 ms | 10.1 ms | 26.32 Mbps | 3/3 | 0.940 |
| 4 | 185.95.231.156:443 | 144 ms | 8.0 ms | 23.92 Mbps | 4/4 | 0.911 |
| 5 | 159.89.87.21:28190 | 154 ms | 1.7 ms | 22.64 Mbps | 3/3 | 0.874 |
| 6 | 169.40.42.231:443 | 169 ms | 7.8 ms | 22.09 Mbps | new | 0.835 |
| 7 | 167.17.69.171:443 | 195 ms | 5.2 ms | 23.14 Mbps | 3/3 | 0.828 |
| 8 | 169.40.42.163:443 | 173 ms | 11.2 ms | 21.87 Mbps | 3/3 | 0.823 |
| 9 | 66.70.179.198:2053 | 175 ms | 12.0 ms | 21.89 Mbps | 5/5 | 0.819 |
| 10 | 159.89.87.21:28190 | 170 ms | 2.3 ms | 21.17 Mbps | 3/3 | 0.818 |

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
