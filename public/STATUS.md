# FreeWave VPN status

- Last run: 2026-11-26 21:43:08 UTC
- TCP candidates: 398
- Tested through Xray: 398
- Passed real HTTPS request through VLESS: 197
- Failed VLESS test: 201
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 197
- Successful 1024 KiB download tests: 158
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 153 ms | 13.0 ms | 25.01 Mbps | 5/5 | 0.948 |
| 2 | 195.123.235.183:443 | 146 ms | 0.7 ms | 22.27 Mbps | 2/2 | 0.911 |
| 3 | 195.123.235.183:443 | 149 ms | 1.5 ms | 22.03 Mbps | 2/2 | 0.899 |
| 4 | 195.123.235.183:443 | 166 ms | 13.0 ms | 21.57 Mbps | 3/3 | 0.852 |
| 5 | 195.123.235.183:443 | 160 ms | 17.0 ms | 21.13 Mbps | 2/2 | 0.849 |
| 6 | 169.40.42.35:443 | 216 ms | 53.9 ms | 23.34 Mbps | 2/2 | 0.817 |
| 7 | 169.40.42.168:443 | 214 ms | 11.4 ms | 21.70 Mbps | new | 0.805 |
| 8 | 169.40.42.231:443 | 259 ms | 23.2 ms | 23.32 Mbps | 2/2 | 0.803 |
| 9 | 169.40.42.35:443 | 256 ms | 17.0 ms | 23.07 Mbps | 3/3 | 0.803 |
| 10 | 169.40.42.89:443 | 219 ms | 6.9 ms | 21.64 Mbps | 5/5 | 0.802 |

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
