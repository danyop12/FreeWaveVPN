# FreeWave VPN status

- Last run: 2026-11-18 07:39:28 UTC
- TCP candidates: 414
- Tested through Xray: 414
- Passed real HTTPS request through VLESS: 164
- Failed VLESS test: 250
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 164
- Successful 1024 KiB download tests: 129
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 15.204.97.214:23576 | 64 ms | 0.4 ms | 56.77 Mbps | 4/4 | 0.999 |
| 2 | 195.123.240.65:443 | 224 ms | 57.1 ms | 22.49 Mbps | 2/3 | 0.388 |
| 3 | 188.114.98.0:443 | 709 ms | 257.4 ms | 22.92 Mbps | new | 0.359 |
| 4 | 185.95.231.156:443 | 277 ms | 0.4 ms | 11.91 Mbps | 2/2 | 0.335 |
| 5 | 188.114.98.0:443 | 977 ms | 404.9 ms | 21.41 Mbps | new | 0.333 |
| 6 | 169.40.42.231:443 | 293 ms | 1.1 ms | 11.95 Mbps | 3/3 | 0.331 |
| 7 | 169.40.42.168:443 | 294 ms | 2.2 ms | 11.98 Mbps | 6/6 | 0.330 |
| 8 | 169.40.42.202:443 | 296 ms | 3.9 ms | 11.61 Mbps | 6/6 | 0.325 |
| 9 | 167.17.69.171:443 | 301 ms | 0.6 ms | 11.44 Mbps | 6/6 | 0.324 |
| 10 | 167.17.69.171:443 | 300 ms | 0.9 ms | 11.28 Mbps | 2/2 | 0.323 |

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
