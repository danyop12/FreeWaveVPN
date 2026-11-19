# FreeWave VPN status

- Last run: 2026-11-19 07:36:45 UTC
- TCP candidates: 361
- Tested through Xray: 361
- Passed real HTTPS request through VLESS: 213
- Failed VLESS test: 148
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 213
- Successful 1024 KiB download tests: 171
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 185.95.231.156:443 | 52 ms | 0.2 ms | 59.26 Mbps | new | 0.996 |
| 2 | 185.95.231.156:443 | 52 ms | 0.3 ms | 52.54 Mbps | 4/4 | 0.931 |
| 3 | 169.40.42.163:443 | 86 ms | 12.5 ms | 57.67 Mbps | 4/4 | 0.844 |
| 4 | 169.40.42.89:443 | 111 ms | 37.9 ms | 57.60 Mbps | 2/2 | 0.783 |
| 5 | 169.40.42.35:443 | 158 ms | 27.7 ms | 59.65 Mbps | 5/5 | 0.776 |
| 6 | 169.40.42.90:443 | 129 ms | 8.6 ms | 55.10 Mbps | 3/3 | 0.769 |
| 7 | 169.40.42.90:443 | 136 ms | 15.5 ms | 55.28 Mbps | 4/4 | 0.758 |
| 8 | 169.40.42.89:443 | 133 ms | 62.2 ms | 55.62 Mbps | 4/4 | 0.732 |
| 9 | 169.40.42.168:443 | 112 ms | 39.4 ms | 50.52 Mbps | 3/3 | 0.716 |
| 10 | 169.40.42.231:443 | 193 ms | 37.9 ms | 54.56 Mbps | 3/3 | 0.709 |

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
