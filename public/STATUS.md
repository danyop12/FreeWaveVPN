# FreeWave VPN status

- Last run: 2026-11-16 22:12:35 UTC
- TCP candidates: 395
- Tested through Xray: 395
- Passed real HTTPS request through VLESS: 192
- Failed VLESS test: 203
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 192
- Successful 1024 KiB download tests: 173
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 170 ms | 6.0 ms | 20.89 Mbps | 3/3 | 0.886 |
| 2 | 195.123.240.65:443 | 184 ms | 23.7 ms | 22.05 Mbps | 2/2 | 0.877 |
| 3 | 23.19.231.244:61019 | 241 ms | 82.0 ms | 22.46 Mbps | new | 0.799 |
| 4 | 195.211.98.43:443 | 182 ms | 6.1 ms | 21.73 Mbps | 2/4 | 0.775 |
| 5 | 195.211.98.43:443 | 186 ms | 11.5 ms | 16.97 Mbps | 4/4 | 0.774 |
| 6 | 167.17.69.171:443 | 205 ms | 3.0 ms | 16.74 Mbps | 6/6 | 0.751 |
| 7 | 185.95.231.156:443 | 188 ms | 7.3 ms | 15.86 Mbps | new | 0.751 |
| 8 | 167.17.69.171:443 | 218 ms | 7.7 ms | 17.50 Mbps | 2/2 | 0.749 |
| 9 | 167.17.69.171:443 | 211 ms | 4.0 ms | 16.97 Mbps | 3/3 | 0.748 |
| 10 | 167.17.69.171:443 | 221 ms | 12.4 ms | 17.38 Mbps | 6/6 | 0.741 |

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
