# FreeWave VPN status

- Last run: 2026-11-08 13:34:04 UTC
- TCP candidates: 415
- Tested through Xray: 415
- Passed real HTTPS request through VLESS: 197
- Failed VLESS test: 218
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 197
- Successful 1024 KiB download tests: 169
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 151 ms | 14.5 ms | 25.97 Mbps | 4/4 | 0.982 |
| 2 | 185.95.231.156:443 | 161 ms | 21.7 ms | 26.20 Mbps | 5/5 | 0.964 |
| 3 | 185.95.231.156:443 | 165 ms | 33.2 ms | 25.36 Mbps | 3/3 | 0.932 |
| 4 | 195.123.235.183:443 | 171 ms | 8.7 ms | 24.06 Mbps | 3/3 | 0.914 |
| 5 | 185.95.231.156:443 | 167 ms | 21.9 ms | 24.20 Mbps | 4/4 | 0.912 |
| 6 | 167.17.69.171:443 | 182 ms | 23.1 ms | 25.04 Mbps | 4/4 | 0.909 |
| 7 | 195.123.235.183:443 | 167 ms | 0.1 ms | 22.78 Mbps | 4/4 | 0.900 |
| 8 | 195.123.235.183:443 | 163 ms | 14.9 ms | 23.04 Mbps | 3/3 | 0.900 |
| 9 | 195.123.235.183:443 | 175 ms | 2.7 ms | 23.31 Mbps | 4/4 | 0.897 |
| 10 | 167.17.69.171:443 | 189 ms | 13.2 ms | 23.81 Mbps | 4/4 | 0.880 |

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
