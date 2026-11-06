# FreeWave VPN for Android

Current Android app version: **0.2.3**.

Native Android client for FreeWave's public VLESS feeds. The app is free to use and does not require a subscription or a paid FreeWave server.

## 0.2.3 — Interleaved public feed discovery

- Downloads all four configured public feeds before applying the candidate limit.
- Interleaves candidates across fast, EU, best and full feeds so the first feed cannot consume all 50 slots.
- Fixes the previous early-exit behavior that could prevent the best/full feed from being read at all.
- This broadens candidate coverage; it does not guarantee that public nodes are alive or trustworthy.

## 0.2.2 — Real HTTPS verification through VLESS

- Removes the raw `1.1.1.1:443` SOCKS CONNECT as a mandatory pass/fail check. A raw IP CONNECT without a TLS ClientHello can be rejected by destination policies even when ordinary HTTPS traffic is allowed.
- Verifies real HTTPS requests through the local SOCKS inbound instead, including TLS/SNI, against Google, Google Static, GitHub and example.com.
- Accepts any valid HTTP response as proof that the VLESS tunnel carried the request; a target's 403/404 response is not mistaken for a broken tunnel.
- Shows the failures from all HTTPS probes only when none can traverse the selected node.

## 0.2.1 — TCP-based first-pass server ranking

- Measures TCP reachability to candidate host/port pairs concurrently (up to 8 checks at once).
- Orders automatic connection attempts by measured TCP connection time, with unreachable/time-out nodes after reachable ones.
- Keeps the selected server as the first candidate when the user explicitly chose one.
- Shows the measured TCP connection time in the connection detail and server picker.
- TCP reachability is only a preliminary signal, not end-to-end VPN latency or proof that a node works. Each candidate must still pass the sing-box tunnel and HTTPS checks before the app reports a successful connection.
- Uses a bounded 9-second ranking window so a large dead feed does not stall connection attempts indefinitely.

## 0.2.0 — Android dashboard and server pool

- Redesigned connection dashboard with a clearer connection state and server controls.
- Merges the fast, Europe, best and full public feeds instead of stopping at the first non-empty feed.
- Keeps up to 50 unique VLESS candidates for automatic failover.
- Remembers the last manually selected or successfully connected node between app launches.
- Automatic mode still requires a successful connection through the local sing-box SOCKS inbound; starting the core alone is not treated as connected.
- Shows a clear warning that public third-party nodes are not suitable for sensitive traffic.

## Architecture

- Kotlin Android client with system VPN tunnel.
- sing-box libbox pinned to 1.14.4.
- VLESS with TLS or REALITY and supported transports implemented in FreeWaveConfigBuilder.
- Public feeds are hosted in this repository under `public/`.
- The app does not operate the exit servers. Feed availability and node quality depend on third parties.

## Build

Requirements: JDK 17, Android SDK Platform 36, Gradle 8.13 and ARM64 Android device (minimum API 26).

The build downloads libbox.aar from the upstream singbox-android/libbox 1.14.4 release and verifies its SHA-256. The binary is not committed.

PowerShell:

    New-Item -ItemType Directory -Force .\android\app\libs | Out-Null
    Invoke-WebRequest -Uri "https://github.com/singbox-android/libbox/releases/download/1.14.4/libbox.aar" -OutFile ".\android\app\libs\libbox.aar"
    $expected = "d2685924a657705beda44e94c6dc499e781df1ad20d65474e3591ec2f8b96465"
    $actual = (Get-FileHash .\android\app\libs\libbox.aar -Algorithm SHA256).Hash.ToLower()
    if ($actual -ne $expected) { throw "libbox.aar checksum mismatch" }
    gradle -p android clean assembleDebug

The APK is written to `android/app/build/outputs/apk/debug/app-debug.apk`. GitHub Actions builds it on pushes to the `android-app` branch and uploads an artifact.

## Connection checks

The app verifies the local SOCKS listener, then tests TCP connectivity to an external IP and a hostname through the tunnel, followed by HTTPS probes. This is a practical reachability test, not a guarantee that every app or website will work.

## Privacy and limitations

Public VLESS nodes are operated by unknown third parties. Operators may observe connection metadata and may be untrustworthy. Do not use public nodes for banking, passwords, private accounts or sensitive traffic. FreeWave does not guarantee anonymity, privacy, speed, availability, or ad removal in the official YouTube app.

A free client cannot guarantee stable service when all available public nodes are offline or overloaded. FreeWave can test nodes and fail over, but cannot create server capacity itself.

## Licensing

The Android app integrates sing-box libbox, distributed under GNU GPL v3; see the repository LICENSE. The libbox AAR is fetched from the upstream release and is not stored in this repository.
