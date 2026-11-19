# FreeWave VPN status

- Last run: 2026-11-19 20:48:22 UTC
- TCP candidates: 366
- Tested through Xray: 366
- Passed real HTTPS request through VLESS: 212
- Failed VLESS test: 154
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 212
- Successful 1024 KiB download tests: 169
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 213 ms | 10.4 ms | 18.20 Mbps | 3/3 | 0.959 |
| 2 | 185.95.231.156:443 | 215 ms | 6.7 ms | 17.70 Mbps | 2/2 | 0.943 |
| 3 | 185.95.231.156:443 | 212 ms | 0.4 ms | 17.32 Mbps | 2/2 | 0.940 |
| 4 | 185.95.231.156:443 | 214 ms | 2.1 ms | 17.33 Mbps | 5/5 | 0.937 |
| 5 | 185.95.231.156:443 | 218 ms | 3.2 ms | 17.45 Mbps | 3/3 | 0.936 |
| 6 | 167.17.69.171:443 | 214 ms | 0.6 ms | 16.80 Mbps | 3/3 | 0.921 |
| 7 | 167.17.69.171:443 | 218 ms | 9.0 ms | 16.77 Mbps | 4/4 | 0.910 |
| 8 | 167.17.69.171:443 | 229 ms | 8.3 ms | 16.58 Mbps | 3/3 | 0.894 |
| 9 | 167.17.69.171:443 | 220 ms | 1.4 ms | 15.80 Mbps | 7/7 | 0.883 |
| 10 | 167.17.69.171:443 | 220 ms | 5.6 ms | 15.23 Mbps | 3/3 | 0.864 |

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
