package app.freewave.vpn

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import io.nekohasekai.libbox.CommandServer
import io.nekohasekai.libbox.CommandServerHandler
import io.nekohasekai.libbox.ConnectionOwner
import io.nekohasekai.libbox.InterfaceUpdateListener
import io.nekohasekai.libbox.Libbox
import io.nekohasekai.libbox.LocalDNSTransport
import io.nekohasekai.libbox.NetworkInterfaceIterator
import io.nekohasekai.libbox.Notification as LibboxNotification
import io.nekohasekai.libbox.OverrideOptions
import io.nekohasekai.libbox.PlatformInterface
import io.nekohasekai.libbox.SetupOptions
import io.nekohasekai.libbox.StringIterator
import io.nekohasekai.libbox.SystemProxyStatus
import io.nekohasekai.libbox.TunOptions
import io.nekohasekai.libbox.WIFIState

class FreeWaveVpnService : VpnService(), PlatformInterface, CommandServerHandler {
    companion object {
        const val ACTION_START = "app.freewave.vpn.START"
        const val ACTION_STOP = "app.freewave.vpn.STOP"
        const val ACTION_STATE = "app.freewave.vpn.STATE"
        const val EXTRA_URI = "node_uri"
        const val EXTRA_STATE = "state"
        const val EXTRA_MESSAGE = "message"
        private const val TAG = "FreeWaveVPN"
        private const val CHANNEL_ID = "freewave_vpn"
        private const val NOTIFICATION_ID = 2401
        @Volatile private var libboxInitialized = false
    }

    @Volatile private var commandServer: CommandServer? = null
    @Volatile private var tunFileDescriptor: ParcelFileDescriptor? = null
    @Volatile private var stopping = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopping = true
                closeTunnel()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelfResult(startId)
                return START_NOT_STICKY
            }
            ACTION_START -> {
                val uri = intent.getStringExtra(EXTRA_URI)
                if (uri.isNullOrBlank()) {
                    publishState("ERROR", "Не передана ссылка сервера")
                    stopSelf()
                    return START_NOT_STICKY
                }
                stopping = false
                startForeground(NOTIFICATION_ID, buildNotification("Подключаем VPN…"))
                closeTunnel()
                publishState("CONNECTING", "Запускаю VPN-ядро")
                Thread({ startTunnel(uri) }, "FreeWave-start").start()
                return START_STICKY
            }
            else -> return START_NOT_STICKY
        }
    }

    private fun startTunnel(uri: String) {
        try {
            initializeLibbox()
            val config = FreeWaveConfigBuilder.build(uri)
            val server = CommandServer(this, this)
            commandServer = server
            server.start()
            Libbox.checkConfig(config)
            server.startOrReloadService(config, OverrideOptions())
            Log.i(TAG, "sing-box service started")
            publishState("CORE_STARTED", "Ядро запущено; проверяю доступ к интернету")
            updateNotification("Проверяю соединение…")
        } catch (error: Throwable) {
            Log.e(TAG, "VPN startup failed", error)
            publishState("ERROR", safeMessage(error))
            closeTunnel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
        }
    }

    private fun initializeLibbox() {
        if (libboxInitialized) return
        synchronized(FreeWaveVpnService::class.java) {
            if (libboxInitialized) return
            val options = SetupOptions().apply {
                setBasePath(filesDir.absolutePath)
                setWorkingPath(getDir("singbox", 0).absolutePath)
                setTempPath(cacheDir.absolutePath)
                setFixAndroidStack(true)
            }
            Libbox.setup(options)
            libboxInitialized = true
            Log.i(TAG, "libbox " + Libbox.version() + " initialized")
        }
    }

    private fun closeTunnel() {
        try { commandServer?.closeService() } catch (error: Throwable) { Log.w(TAG, "Could not close sing-box service") }
        try { commandServer?.close() } catch (error: Throwable) { Log.w(TAG, "Could not close command server") }
        commandServer = null
        try { tunFileDescriptor?.close() } catch (_: Throwable) {}
        tunFileDescriptor = null
        publishState("STOPPED", "VPN отключён")
    }

    override fun onDestroy() {
        stopping = true
        closeTunnel()
        super.onDestroy()
    }

    override fun onRevoke() {
        stopping = true
        closeTunnel()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        super.onRevoke()
    }

    override fun openTun(options: TunOptions): Int {
        val builder = Builder().setSession("FreeWave VPN").setMtu(options.mtu.takeIf { it in 576..9000 } ?: 1400)
        val v4Addresses = mutableListOf<Pair<String, Int>>()
        val it4 = options.inet4Address
        while (it4.hasNext()) { val p = it4.next(); v4Addresses.add(p.address() to p.prefix()) }
        val v6Addresses = mutableListOf<Pair<String, Int>>()
        val it6 = options.inet6Address
        while (it6.hasNext()) { val p = it6.next(); v6Addresses.add(p.address() to p.prefix()) }
        v4Addresses.forEach { builder.addAddress(it.first, it.second) }
        v6Addresses.forEach { builder.addAddress(it.first, it.second) }
        val v4Routes = mutableListOf<Pair<String, Int>>()
        val r4 = options.inet4RouteAddress
        while (r4.hasNext()) { val p = r4.next(); v4Routes.add(p.address() to p.prefix()) }
        val v6Routes = mutableListOf<Pair<String, Int>>()
        val r6 = options.inet6RouteAddress
        while (r6.hasNext()) { val p = r6.next(); v6Routes.add(p.address() to p.prefix()) }
        v4Routes.forEach { builder.addRoute(it.first, it.second) }
        v6Routes.forEach { builder.addRoute(it.first, it.second) }
        if (v4Routes.none { it.second == 0 }) builder.addRoute("0.0.0.0", 0)
        if (v6Addresses.isNotEmpty() && v6Routes.none { it.second == 0 }) builder.addRoute("::", 0)
        if (options.dnsMode.value != Libbox.DNSModeDisabled) {
            val dnsServers = options.dnsServerAddress
            while (dnsServers.hasNext()) {
                val address = dnsServers.next()
                if (address.isNotBlank()) builder.addDnsServer(address)
            }
        }
        builder.addDnsServer("1.1.1.1")
        runCatching { builder.addDisallowedApplication(packageName) }
        tunFileDescriptor = builder.establish()
            ?: throw IllegalStateException("Android не выдал VPN-интерфейс. Проверь разрешение VPN.")
        return tunFileDescriptor!!.fd
    }

    override fun autoDetectInterfaceControl(fd: Int) {
        if (!protect(fd)) throw IllegalStateException("Не удалось защитить исходящий сокет VPN")
    }
    override fun usePlatformAutoDetectInterfaceControl(): Boolean = true
    override fun underNetworkExtension(): Boolean = false
    override fun includeAllNetworks(): Boolean = false
    override fun findConnectionOwner(ipProtocol: Int, sourceAddress: String, sourcePort: Int,
        destinationAddress: String, destinationPort: Int): ConnectionOwner? = ConnectionOwner()
    override fun readWIFIState(): WIFIState? = null
    override fun getInterfaces(): NetworkInterfaceIterator? = null
    override fun startDefaultInterfaceMonitor(listener: InterfaceUpdateListener?) {}
    override fun closeDefaultInterfaceMonitor(listener: InterfaceUpdateListener?) {}
    override fun clearDNSCache() {}
    override fun sendNotification(notification: LibboxNotification?) {}
    override fun localDNSTransport(): LocalDNSTransport? = null
    override fun serviceReload() { publishState("CORE_RELOADED", "Настройки VPN обновлены") }
    override fun serviceStop() {
        if (!stopping) publishState("ERROR", "VPN-ядро остановило соединение")
    }
    override fun getSystemProxyStatus(): SystemProxyStatus? = null
    override fun setSystemProxyEnabled(enabled: Boolean) {}
    override fun writeDebugMessage(message: String?) {
        Log.d(TAG, "sing-box diagnostic event")
    }

    // Optional root, SSH and bridge facilities are intentionally unsupported by this client.
    override fun cancelNotification(identifier: String?, typeID: Int) {}
    override fun checkPlatformShell() {}
    override fun closeNeighborMonitor(listener: io.nekohasekai.libbox.NeighborUpdateListener?) {}
    override fun createBridge(options: io.nekohasekai.libbox.BridgeOptions?): io.nekohasekai.libbox.BridgeSession {
        throw UnsupportedOperationException("Bridge mode is unavailable on Android")
    }
    override fun lookupSFTPServer(): String {
        throw UnsupportedOperationException("SFTP is unavailable")
    }
    override fun lookupUser(username: String?): io.nekohasekai.libbox.PlatformUser {
        throw UnsupportedOperationException("Platform shell is unavailable")
    }
    override fun openShellSession(user: io.nekohasekai.libbox.PlatformUser?, command: String?, environ: StringIterator?, term: String?, rows: Int, cols: Int): io.nekohasekai.libbox.ShellSession {
        throw UnsupportedOperationException("Platform shell is unavailable")
    }
    override fun readSystemSSHHostKey(): String {
        throw UnsupportedOperationException("SSH is unavailable")
    }
    override fun registerMyInterface(name: String?) {}
    override fun startNeighborMonitor(listener: io.nekohasekai.libbox.NeighborUpdateListener?) {}
    override fun tailscaleHostname(): String = ""
    override fun usePlatformBridge(): Boolean = false
    override fun usePlatformShell(): Boolean = false
    override fun connectSSHAgent(): Int = -1
    override fun triggerNativeCrash() {}

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = NotificationChannel(CHANNEL_ID, "FreeWave VPN", NotificationManager.IMPORTANCE_LOW)
            .apply { description = "Состояние VPN-подключения"; setShowBadge(false) }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }
    private fun buildNotification(message: String): Notification =
        Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("FreeWave VPN")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setOngoing(true)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
    private fun updateNotification(message: String) {
        getSystemService(NotificationManager::class.java).notify(NOTIFICATION_ID, buildNotification(message))
    }
    private fun publishState(state: String, message: String) {
        sendBroadcast(Intent(ACTION_STATE).setPackage(packageName)
            .putExtra(EXTRA_STATE, state).putExtra(EXTRA_MESSAGE, message))
    }
    private fun safeMessage(error: Throwable): String {
        val raw = error.message.orEmpty()
        val scrubbed = raw.replace(Regex("(?i)(uuid|password|token|secret)[=: ]+[^,; ]+"), "credential=<hidden>").take(180)
        return scrubbed.ifBlank { "Не удалось запустить VPN-ядро" }
    }
}
