# FreeWave VPN status

- Last run: 2026-11-13 19:52:54 UTC
- TCP candidates: 407
- Tested through Xray: 407
- Passed real HTTPS request through VLESS: 233
- Failed VLESS test: 174
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 233
- Successful 1024 KiB download tests: 147
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 135 ms | 4.0 ms | 24.61 Mbps | 2/2 | 0.974 |
| 2 | 185.95.231.156:443 | 153 ms | 20.9 ms | 24.46 Mbps | 5/5 | 0.922 |
| 3 | 159.89.87.21:28190 | 145 ms | 0.3 ms | 22.88 Mbps | 4/4 | 0.919 |
| 4 | 167.17.69.171:443 | 190 ms | 6.4 ms | 25.35 Mbps | 5/5 | 0.903 |
| 5 | 159.89.87.21:28190 | 144 ms | 2.3 ms | 21.22 Mbps | 2/2 | 0.883 |
| 6 | 159.89.87.21:28190 | 152 ms | 3.3 ms | 21.76 Mbps | 5/5 | 0.880 |
| 7 | 167.17.69.171:443 | 194 ms | 8.5 ms | 24.47 Mbps | 2/2 | 0.878 |
| 8 | 159.89.87.21:28190 | 171 ms | 4.0 ms | 22.34 Mbps | 3/3 | 0.863 |
| 9 | 169.40.42.163:443 | 174 ms | 11.9 ms | 22.39 Mbps | 4/4 | 0.854 |
| 10 | 167.17.69.171:443 | 196 ms | 3.2 ms | 23.16 Mbps | 3/3 | 0.852 |

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
