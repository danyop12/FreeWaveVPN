# FreeWave VPN status

- Last run: 2026-11-22 07:01:06 UTC
- TCP candidates: 413
- Tested through Xray: 413
- Passed real HTTPS request through VLESS: 163
- Failed VLESS test: 250
- Not tested due to limit: 0
- Nodes confirmed by at least 3 separate HTTPS probes: 163
- Successful 1024 KiB download tests: 141
- Nodes geolocated to Albania (AL): 0

## Top nodes from this GitHub Actions run

| # | Server | Median latency | Jitter (MAD) | Download | History | Score |
|---:|---|---:|---:|---:|---:|---:|
| 1 | 195.123.240.65:443 | 193 ms | 15.6 ms | 19.07 Mbps | 5/7 | 0.765 |
| 2 | 169.40.42.231:443 | 343 ms | 30.9 ms | 12.26 Mbps | 5/5 | 0.567 |
| 3 | 169.40.42.90:443 | 344 ms | 41.1 ms | 12.12 Mbps | 4/4 | 0.559 |
| 4 | 169.40.42.35:443 | 336 ms | 37.7 ms | 11.96 Mbps | 4/4 | 0.557 |
| 5 | 169.40.42.202:443 | 344 ms | 35.9 ms | 11.96 Mbps | 5/5 | 0.557 |
| 6 | 169.40.42.90:443 | 415 ms | 11.4 ms | 12.05 Mbps | 6/6 | 0.556 |
| 7 | 169.40.42.231:443 | 284 ms | 1.1 ms | 10.86 Mbps | 5/5 | 0.555 |
| 8 | 169.40.42.163:443 | 417 ms | 11.1 ms | 11.99 Mbps | 3/3 | 0.554 |
| 9 | 169.40.42.229:443 | 286 ms | 2.6 ms | 10.90 Mbps | 4/4 | 0.554 |
| 10 | 169.40.42.74:443 | 416 ms | 18.2 ms | 11.99 Mbps | 2/2 | 0.552 |

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
