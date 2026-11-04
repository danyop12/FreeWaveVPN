# FreeWave VPN 0.3.1

FreeWave VPN collects public VLESS links from configured HTTPS sources, removes
duplicates, checks only the exact endpoints published in those links, and
publishes subscriptions for nodes that pass two independent Xray VLESS + HTTPS probes.

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

- `freewave-vless.txt` — nodes that passed two independent HTTPS probes through Xray.
- `freewave-best.txt` — the single highest-ranked VLESS key for clients that import one key.
- `freewave-best-base64.txt` — Base64 version of that single key.
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
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-best.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-best-base64.txt
```

The subscription files may be empty when no node passes the real VLESS check.
That is intentional: TCP-only endpoints are not published as verified VPN nodes.


## Android client notes

- **v2RayTun:** import `freewave-vless.txt` for the validated server pool, or
  `freewave-best.txt` as a one-node subscription when you want FreeWave to preselect
  the latest top-ranked node. The best-key URL is refreshed on the six-hour workflow.
  It is not instant failover: a node can fail between updates. Native auto-selection
  and live switching depend on the features exposed by your installed app version.
- **AvoVPN:** its published Google Play instructions describe importing a single
  `vless://` key. Use `freewave-best.txt` and paste its one VLESS link as a key.
  Do not paste the subscription URL where the app expects a single key.

The GitHub-runner ranking is only a starting point. Your phone may see a different
fastest node because routes and performance vary by network and location. The best-key
feed is a periodic preselection, not a guarantee of the fastest node on your own ISP.

## Android mobile app

The repository includes an installable mobile web app in `public/`. It does not require Python on Android: the heavier candidate collection and Xray checks continue to run in GitHub Actions, while the mobile page reads the already-published feeds and lets you copy or open VLESS keys.

### Enable the mobile page once

1. Open **Settings → Pages** in this repository.
2. Under **Build and deployment → Source**, select **GitHub Actions**.
3. Open **Actions → FreeWave Pages → Run workflow**. The page is also redeployed after a successful **FreeWave update** run.
4. Open [https://danyop12.github.io/FreeWaveVPN/](https://danyop12.github.io/FreeWaveVPN/) in Chrome on Android.
5. In Chrome's menu, choose **Install app** or **Add to Home screen**.

The mobile page shows the last verified-node count, top configurations, and copyable subscription URLs. Tapping a `vless://` key can hand it to a compatible VPN client when that client has registered the link type.

**Important:** the mobile page is a companion dashboard, not a VPN engine. It cannot create Android's system VPN tunnel by itself. Install a compatible client such as V2RayTun or AvoVPN to make the actual connection. It also does not run the Python collector on the phone.

## Personal Android connection

For a simple one-phone setup, use the full validated subscription rather than importing the single `freewave-best.txt` key:

- Open the mobile dashboard: [https://danyop12.github.io/FreeWaveVPN/](https://danyop12.github.io/FreeWaveVPN/).
- Install [v2rayNG](https://github.com/2dust/v2rayNG/releases/latest) from its official releases and use **Add to v2rayNG** on the dashboard to import `freewave-vless.txt`. After import, open the FreeWave group in v2rayNG and run **Update current group subscription** once if the profiles are not displayed yet.
- In the subscription group's settings, enable automatic updating. In the app's general settings, enable automatic subscription updates and choose 360 minutes. FreeWave's GitHub workflow refreshes its generated list every six hours.
- Enable **Auto test after updating subscription** and **Auto sort after test** so v2rayNG measures the servers from the phone's own network, rather than relying only on GitHub-runner rankings.
- In v2rayNG VPN settings, enable **Connect after device boot**. Then open Android **Settings → VPN → v2rayNG** and enable **Always-on VPN** if the device exposes that setting. Optionally add the v2rayNG Quick Settings tile for a one-tap manual connect/disconnect.
- On Infinix/XOS, open **Settings → Apps → App Management → v2rayNG → Battery**, allow background activity, and set battery use to **Unrestricted**. Also enable v2rayNG in Auto-start management and exclude it from battery optimization. Menu labels vary by XOS version; search Settings for “autostart”, “background activity”, and “battery”.

Android's **Block connections without VPN** option is optional. It acts as a kill switch, but if the chosen node stops working it can block all internet access until the VPN reconnects. Test normal connection and recovery before enabling it.

The browser dashboard cannot silently start a VPN tunnel. Android requires the VPN client and the operating system's connection permission; the one-tap v2rayNG link only imports the subscription. Boot auto-connect is handled by v2rayNG and Android.

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

The GitHub repository, Pages dashboard, and generated subscription URLs are public, so anyone can read and import these public configurations. FreeWave is a personal dashboard for convenience, not a private VPN server or a private account.

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
