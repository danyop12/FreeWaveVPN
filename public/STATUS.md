# FreeWave VPN status

- Last run: 2026-11-18 20:41:49 UTC
- TCP candidates: 395
- Tested through Xray: 395
- Passed real HTTPS request through VLESS: 148
- Failed VLESS test: 247
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 148
- Successful 1024 KiB download tests: 103
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 158 ms | 24.8 ms | 26.04 Mbps | 3/3 | 0.964 |
| 2 | 185.95.231.156:443 | 150 ms | 16.9 ms | 22.99 Mbps | 3/3 | 0.920 |
| 3 | 169.40.42.202:443 | 172 ms | 3.8 ms | 22.00 Mbps | 6/6 | 0.872 |
| 4 | 159.89.87.21:28190 | 170 ms | 18.4 ms | 20.79 Mbps | 2/2 | 0.839 |
| 5 | 167.17.69.171:443 | 218 ms | 10.6 ms | 23.09 Mbps | 3/3 | 0.837 |
| 6 | 167.17.69.171:443 | 214 ms | 18.5 ms | 22.92 Mbps | 7/7 | 0.833 |
| 7 | 159.89.87.21:28190 | 183 ms | 23.9 ms | 20.84 Mbps | 2/2 | 0.818 |
| 8 | 169.40.42.163:443 | 179 ms | 11.5 ms | 20.08 Mbps | 2/2 | 0.817 |
| 9 | 169.40.42.231:443 | 229 ms | 2.3 ms | 22.30 Mbps | 4/4 | 0.816 |
| 10 | 167.17.69.171:443 | 224 ms | 27.9 ms | 22.69 Mbps | 3/3 | 0.814 |

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
