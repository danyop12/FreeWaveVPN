# FreeWave VPN status

- Last run: 2026-11-28 07:59:12 UTC
- TCP candidates: 439
- Tested through Xray: 439
- Passed real HTTPS request through VLESS: 149
- Failed VLESS test: 290
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 149
- Successful 1024 KiB download tests: 111
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 169.40.42.229:443 | 183 ms | 65.0 ms | 30.19 Mbps | new | 0.741 |
| 2 | 169.40.42.168:443 | 244 ms | 70.5 ms | 30.59 Mbps | 2/2 | 0.732 |
| 3 | 169.40.42.74:443 | 175 ms | 10.1 ms | 27.71 Mbps | 2/2 | 0.731 |
| 4 | 209.200.246.148:443 | 155 ms | 3.5 ms | 25.54 Mbps | new | 0.709 |
| 5 | 209.200.246.148:443 | 162 ms | 14.6 ms | 25.44 Mbps | 3/3 | 0.694 |
| 6 | 169.40.42.202:443 | 180 ms | 2.4 ms | 25.03 Mbps | 3/3 | 0.687 |
| 7 | 169.40.42.90:443 | 240 ms | 37.6 ms | 27.00 Mbps | 3/3 | 0.682 |
| 8 | 169.40.42.163:443 | 164 ms | 39.2 ms | 25.52 Mbps | 4/4 | 0.677 |
| 9 | 169.40.42.90:443 | 179 ms | 7.7 ms | 24.63 Mbps | 4/4 | 0.676 |
| 10 | 169.40.42.89:443 | 182 ms | 65.3 ms | 26.55 Mbps | 3/3 | 0.676 |

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
