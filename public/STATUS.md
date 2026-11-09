# FreeWave VPN status

- Last run: 2026-11-09 06:43:23 UTC
- TCP candidates: 430
- Tested through Xray: 430
- Passed real HTTPS request through VLESS: 231
- Failed VLESS test: 199
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 231
- Successful 1024 KiB download tests: 189
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 172.67.199.126:2053 | 214 ms | 6.1 ms | 34.02 Mbps | new | 0.807 |
| 2 | 104.21.58.37:2053 | 220 ms | 17.7 ms | 33.35 Mbps | new | 0.786 |
| 3 | 195.211.98.43:443 | 143 ms | 68.9 ms | 26.27 Mbps | 2/2 | 0.751 |
| 4 | 195.123.235.183:443 | 153 ms | 6.3 ms | 24.41 Mbps | 2/2 | 0.750 |
| 5 | 195.123.235.183:443 | 147 ms | 2.3 ms | 22.31 Mbps | 3/3 | 0.735 |
| 6 | 167.17.69.171:443 | 157 ms | 7.3 ms | 23.68 Mbps | 3/3 | 0.733 |
| 7 | fast.145779.xyz:2053 | 193 ms | 23.8 ms | 39.95 Mbps | 1/4 | 0.729 |
| 8 | 167.17.69.171:443 | 153 ms | 1.6 ms | 19.23 Mbps | 6/6 | 0.683 |
| 9 | 169.40.42.74:443 | 164 ms | 1.2 ms | 20.35 Mbps | 4/4 | 0.680 |
| 10 | 169.40.42.90:443 | 171 ms | 15.9 ms | 21.25 Mbps | 3/3 | 0.671 |

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
