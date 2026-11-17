# FreeWave VPN status

- Last run: 2026-11-17 21:04:48 UTC
- TCP candidates: 406
- Tested through Xray: 406
- Passed real HTTPS request through VLESS: 139
- Failed VLESS test: 267
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 139
- Successful 1024 KiB download tests: 110
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.214:23576 | 57 ms | 7.2 ms | 55.94 Mbps | 3/3 | 0.983 |
| 2 | 169.40.42.168:443 | 291 ms | 9.6 ms | 11.68 Mbps | 5/5 | 0.318 |
| 3 | 169.40.42.163:443 | 283 ms | 5.2 ms | 11.19 Mbps | 4/4 | 0.317 |
| 4 | 169.40.42.231:443 | 349 ms | 1.6 ms | 12.01 Mbps | 2/2 | 0.316 |
| 5 | 167.17.69.171:443 | 303 ms | 3.1 ms | 10.95 Mbps | 5/5 | 0.312 |
| 6 | 167.17.69.171:443 | 306 ms | 0.1 ms | 10.81 Mbps | 4/4 | 0.312 |
| 7 | 167.17.69.171:443 | 313 ms | 8.2 ms | 10.91 Mbps | 3/3 | 0.308 |
| 8 | 169.40.42.202:443 | 398 ms | 12.4 ms | 11.53 Mbps | 5/5 | 0.301 |
| 9 | 169.40.42.89:443 | 414 ms | 23.0 ms | 11.66 Mbps | 6/6 | 0.298 |
| 10 | 169.40.42.74:443 | 345 ms | 4.5 ms | 10.22 Mbps | 5/5 | 0.298 |

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
