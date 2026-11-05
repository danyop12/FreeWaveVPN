# FreeWave VPN for Android

Native Android client for the FreeWave public VLESS feeds.

## Milestone 0.1.0

- Kotlin application with Android system VPN tunnel.
- sing-box libbox pinned to 1.14.4.
- Downloads freewave-fast.txt, falls back to freewave-vless.txt.
- Automatic mode tries up to 10 nodes and only accepts one after an HTTPS request succeeds through the local sing-box SOCKS inbound.
- Manual node selection is available for troubleshooting.
- The connected state is not shown merely because the core starts.

This is an initial personal-use build. Network-change recovery, boot auto-connect, per-app routing and detailed diagnostics are later milestones.

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

The APK is written to android/app/build/outputs/apk/debug/app-debug.apk. GitHub Actions also builds it on pushes to the android-app branch and uploads an artifact.

## Connection checks

The app checks tunnel traffic through a local SOCKS inbound at 127.0.0.1:2080. It performs HTTPS probes to Google static content and example.com. This is only a limited reachability check, not a guarantee that all sites and applications work.

## Limitations and safety

FreeWave does not operate the exit servers. The feed contains public third-party VLESS nodes that may disappear, be overloaded, monitor metadata or be unsafe. Do not use them for banking, passwords, private accounts or sensitive traffic. The app does not guarantee anonymity, privacy, speed, availability or ad removal in the official YouTube app.

The initial Android client supports VLESS links with TLS or REALITY and the transports implemented in FreeWaveConfigBuilder. Unsupported or malformed links may not be usable.

## Licensing

The Android app integrates sing-box libbox, distributed under GNU GPL v3. The Android client module and its source are therefore provided under GPL-3.0; see LICENSE. The rest of the FreeWaveVPN repository retains its existing licensing terms. The libbox AAR is fetched from the upstream release and is not stored in this repository.
