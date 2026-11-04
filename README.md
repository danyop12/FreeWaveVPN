# FreeWave VPN 0.2.1

FreeWave VPN collects public VLESS links from configured HTTPS sources, removes
duplicates, checks only the exact endpoints published in those links, and
publishes subscriptions for nodes that pass an actual Xray VLESS + HTTPS test.

## How the pipeline works

1. Fetch public subscriptions from `sources.txt` and decode plain-text or Base64.
2. Extract only `vless://` links, validate URI host/port, and remove duplicates.
3. Resolve each advertised hostname and reject endpoints whose DNS answers include
   private, loopback, link-local, multicast, reserved, or otherwise non-global IPs.
4. Make a TCP connection attempt only to the exact public IP and port in each URI.
5. Run a pinned Xray-core client against an evenly distributed sample of TCP
   candidates. A node is considered validated only after an HTTPS request succeeds
   through its VLESS tunnel.
6. Require two independent HTTPS destinations to respond through the same VLESS tunnel.
7. Measure a small download using curl's actual average transfer-rate field and rank
   passing nodes by a relative score (65% measured download rate, 35% HTTPS response time).

The current workflow tests up to all 500 evenly distributed candidates per run (or every candidate when fewer are available). The rest
remain marked as untested in `metrics.json`. Each accepted node must pass two separate
HTTPS probes; a successful TCP connection alone is never enough. A new run happens every six hours.

## Subscription files

Generated files are committed under `public/` after each successful workflow:

- `freewave-vless.txt` — only nodes that passed the Xray VLESS + HTTPS test.
- `freewave-vless-base64.txt` — Base64 version of the validated subscription.
- `freewave-fast.txt` — up to 25 highest-ranked validated nodes.
- `freewave-fast-base64.txt` — Base64 version of the fast list.
- `freewave-tcp.txt` — preliminary TCP-only candidates; **not a working-node list**.
- `freewave-eu.txt` — name-based heuristic subset, not verified geolocation.
- `metrics.json` — tested counts, response times, download rates, score and failure reasons.
- `STATUS.md` — latest run summary.

Subscription URLs:

```text
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-vless.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-vless-base64.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-fast.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-fast-base64.txt
```

The subscription files may be empty when no node passes the real VLESS check.
That is intentional: TCP-only endpoints are not published as verified VPN nodes.


## Android client notes

- **v2RayTun:** import `freewave-vless.txt` as a subscription URL. The list is sorted
  by the latest GitHub-runner score. Whether the app automatically chooses or switches
  nodes depends on the features exposed by the installed version; the subscription
  itself cannot force an app's auto-selection behavior.
- **AvoVPN:** its published Google Play instructions describe importing a single
  `vless://` key. Use `freewave-best.txt` and paste its one VLESS link as a key.
  Do not paste the subscription URL where the app expects a single key.

The GitHub-runner ranking is only a starting point. Your phone may see a different
fastest node because routes and performance vary by network and location.

## Run locally

Requires Python 3.10+:

```bash
python -m freewave.build
```

This performs only the preliminary TCP scan. The full VLESS and speed stage also
requires an Xray-core binary and `curl`:

```bash
XRAY_PATH=/path/to/xray python -m freewave.rank
```

## GitHub Actions

The workflow runs on manual dispatch, relevant source/code changes, and every
six hours. Xray-core is pinned to a specific release and its archive SHA-256 is
verified before execution. The workflow has `contents: write` permission to
commit the generated subscription and status files.

To run it manually, open **Actions → FreeWave update → Run workflow**.

## Important limitations

FreeWave does not own, operate, or control any listed server. Nodes are run by
unknown third parties and may log metadata, inspect unencrypted traffic, limit
usage, or disappear without notice. Do not use public nodes for sensitive work.

A passing HTTPS probe proves only that one HTTPS request succeeded through that
VLESS node from the GitHub Actions runner at that time. It does not prove that
all websites, apps, UDP, streaming, or future sessions will work. Download rate
and response time are measured from GitHub infrastructure, not from your phone;
your local ISP, country, route, device, and time of day can produce very
different results.

The `fast` subscription is a list sorted by the latest GitHub-runner measurements.
It does not force V2RayTun or AvoVPN to switch servers automatically. Actual
automatic selection depends on whether the client supports a URL-test or
auto-select group.

There is no guarantee of unlimited traffic, speed, privacy, or uptime. The
project itself is not a VPN exit server and does not carry your traffic.

## Sources

The configured upstreams are listed in `sources.txt`. They are community
repositories with public third-party configurations. Only publicly advertised
VLESS endpoints are tested; the collector does not scan IP ranges.
