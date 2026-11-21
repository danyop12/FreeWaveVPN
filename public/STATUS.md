# FreeWave VPN status

- Last run: 2026-11-21 19:26:05 UTC
- TCP candidates: 423
- Tested through Xray: 423
- Passed real HTTPS request through VLESS: 153
- Failed VLESS test: 270
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 153
- Successful 1024 KiB download tests: 130
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 166 ms | 5.6 ms | 22.37 Mbps | 2/2 | 0.971 |
| 2 | 185.95.231.156:443 | 180 ms | 5.9 ms | 22.84 Mbps | 3/3 | 0.960 |
| 3 | 185.95.231.156:443 | 171 ms | 5.9 ms | 20.99 Mbps | 3/3 | 0.928 |
| 4 | 169.40.42.74:443 | 173 ms | 2.5 ms | 19.86 Mbps | 5/5 | 0.901 |
| 5 | 167.17.69.171:443 | 220 ms | 5.1 ms | 22.20 Mbps | 6/6 | 0.897 |
| 6 | 167.17.69.171:443 | 212 ms | 7.6 ms | 21.88 Mbps | 3/3 | 0.896 |
| 7 | 169.40.42.168:443 | 180 ms | 21.7 ms | 19.33 Mbps | 3/3 | 0.863 |
| 8 | 169.40.42.202:443 | 228 ms | 9.0 ms | 19.64 Mbps | 4/4 | 0.826 |
| 9 | 169.40.42.163:443 | 227 ms | 10.7 ms | 19.15 Mbps | 2/2 | 0.814 |
| 10 | 169.40.42.90:443 | 219 ms | 1.3 ms | 18.31 Mbps | 4/4 | 0.807 |

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
