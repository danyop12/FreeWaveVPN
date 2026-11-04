# FreeWave VPN

FreeWave VPN is a public VLESS configuration aggregator. It downloads VLESS
links from the HTTPS sources listed in `sources.txt`, removes duplicates,
performs a conservative TCP reachability check, and writes subscription files.

## Important limitations

FreeWave does not own or operate these servers. Public nodes are controlled by
unknown third parties and may log metadata, restrict traffic, or disappear.
Unlimited traffic, speed, privacy, and uptime are not guaranteed. Do not use
public nodes for sensitive activity.

A successful TCP check only means that a TCP connection was accepted at the
time of testing. It does not prove a VLESS handshake, TLS/REALITY validation,
authentication, speed, or privacy. The optional EU list uses configuration
name hints only; it is not verified geolocation.

## Run locally

Requires Python 3.10+ and no third-party packages:

```bash
python -m freewave.build
```

Generated files are written to `public/`:
- `freewave-vless.txt` — plain VLESS links
- `freewave-vless-base64.txt` — Base64 subscription
- `freewave-eu.txt` — heuristic name-based subset
- `STATUS.md` — last successful build summary

If no TCP-reachable nodes are found, the previous output files are preserved.

## GitHub Actions

The workflow runs manually, after relevant pushes, and every six hours.

1. Enable GitHub Actions in repository settings.
2. Open **Actions → FreeWave update → Run workflow** for the first run.
3. After a successful run, the subscription URLs will be:

```text
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-vless.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-vless-base64.txt
https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/freewave-eu.txt
```

These files do not exist until the first successful workflow run commits them.

## Sources

Edit `sources.txt` to add or remove public HTTPS subscription sources.
Only use sources you are legally allowed to access.
