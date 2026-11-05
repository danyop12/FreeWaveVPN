package app.freewave.vpn

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.VpnService
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : Activity() {
    private data class Node(val uri: String, val name: String, val host: String, val port: Int)
    companion object {
        private const val VPN_REQUEST = 4107
        private const val BASE_URL = "https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/"
        private const val MAX_NODES = 25
    }
    private val executor = Executors.newSingleThreadExecutor()
    private var nodes: List<Node> = emptyList()
    private var preferredNode: Node? = null
    private var connecting = false
    private var connected = false
    @Volatile private var coreReady = false
    @Volatile private var coreFailure: String? = null
    private lateinit var statusTitle: TextView
    private lateinit var statusDetail: TextView
    private lateinit var serverText: TextView
    private lateinit var countText: TextView
    private lateinit var connectButton: Button
    private lateinit var refreshButton: Button
    private lateinit var chooseButton: Button

    private val stateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val state = intent?.getStringExtra(FreeWaveVpnService.EXTRA_STATE).orEmpty()
            val message = intent?.getStringExtra(FreeWaveVpnService.EXTRA_MESSAGE).orEmpty()
            when (state) {
                "CORE_STARTED" -> {
                    coreReady = true
                    runOnUiThread {
                        if (connecting) {
                            statusTitle.text = "Ядро запущено · проверяю интернет"
                            statusDetail.text = "Проверяю HTTPS через VPN-туннель…"
                        }
                    }
                }
                "ERROR" -> {
                    coreFailure = message.ifBlank { "Ядро остановило подключение без подробной причины" }
                    coreReady = false
                    runOnUiThread {
                        if (connecting) {
                            statusTitle.text = "Этот сервер не запустился"
                            statusDetail.text = coreFailure
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildInterface()
        loadNodes()
    }
    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(FreeWaveVpnService.ACTION_STATE)
        if (Build.VERSION.SDK_INT >= 33) registerReceiver(stateReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        else {
            @Suppress("DEPRECATION")
            registerReceiver(stateReceiver, filter)
        }
    }
    override fun onStop() {
        runCatching { unregisterReceiver(stateReceiver) }
        super.onStop()
    }
    override fun onDestroy() {
        executor.shutdownNow()
        super.onDestroy()
    }

    private fun buildInterface() {
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(22), dp(24), dp(22), dp(20))
            setBackgroundColor(Color.rgb(11, 16, 32))
        }
        val scroll = ScrollView(this).apply { isFillViewport = true; addView(page) }
        setContentView(scroll)
        val brand = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val brandIcon = ImageView(this).apply {
            setImageResource(R.drawable.ic_launcher_foreground)
            scaleType = ImageView.ScaleType.FIT_CENTER
            contentDescription = "FreeWave VPN"
            layoutParams = LinearLayout.LayoutParams(dp(52), dp(52))
        }
        val brandText = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(text("FREEWAVE VPN", 16, "#55D6BE", true))
            addView(text("Личный VPN-клиент", 12, "#A8B2C4"), margin(top = 3))
        }
        brand.addView(brandIcon, LinearLayout.LayoutParams(dp(52), dp(52)).apply { marginEnd = dp(12) })
        brand.addView(brandText)
        page.addView(brand, margin(bottom = 18))
        page.addView(text("VPN без лишних настроек", 25, "#F4F7FB", true), margin(top = 2))
        page.addView(text("Автоматически ищет сервер, через который действительно открываются сайты.", 14, "#A8B2C4"), margin(top = 8, bottom = 24))

        val statusCard = card()
        statusCard.addView(text("СОСТОЯНИЕ", 11, "#7D8AA3", true))
        statusTitle = text("Загружаю список серверов…", 21, "#F4F7FB", true)
        statusDetail = text("Получаю проверенные конфигурации FreeWave.", 13, "#A8B2C4")
        statusCard.addView(statusTitle, margin(top = 12))
        statusCard.addView(statusDetail, margin(top = 6))
        connectButton = Button(this).apply {
            text = "Подключиться автоматически"
            isAllCaps = false
            textSize = 15f
            setTextColor(Color.rgb(8, 25, 28))
            background = rounded("#55D6BE", 18)
            setOnClickListener { if (connected) disconnect() else requestVpnPermission() }
        }
        statusCard.addView(connectButton, margin(top = 22, bottom = 8))
        page.addView(statusCard, margin(bottom = 16))

        val serverCard = card()
        serverCard.addView(text("ВЫБОР СЕРВЕРА", 11, "#7D8AA3", true))
        serverText = text("Автоматически · лучший доступный", 16, "#F4F7FB", true)
        countText = text("Список ещё загружается", 13, "#A8B2C4")
        serverCard.addView(serverText, margin(top = 10))
        serverCard.addView(countText, margin(top = 5))
        val actions = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL }
        refreshButton = smallButton("Обновить") { loadNodes() }
        chooseButton = smallButton("Серверы") { showServerPicker() }
        actions.addView(refreshButton, LinearLayout.LayoutParams(0, dp(48), 1f).apply { marginEnd = dp(6) })
        actions.addView(chooseButton, LinearLayout.LayoutParams(0, dp(48), 1f).apply { marginStart = dp(6) })
        serverCard.addView(actions, margin(top = 16))
        page.addView(serverCard, margin(bottom = 16))

        val infoCard = card()
        infoCard.addView(text("КАК РАБОТАЕТ АВТОРЕЖИМ", 11, "#7D8AA3", true))
        infoCard.addView(text("FreeWave проверяет узлы по очереди через настоящий HTTPS-запрос. Если сервер не пропускает интернет, приложение пробует следующий.", 13, "#C1CAD8"), margin(top = 10))
        page.addView(infoCard, margin(bottom = 16))
        page.addView(text("Используются публичные серверы третьих лиц. Они могут отключаться и не гарантируют приватность. Не передавай через них пароли, банковские данные и другую чувствительную информацию.", 12, "#77839A"), margin(top = 4))
        page.addView(text("FreeWave VPN · " + BuildConfig.VERSION_NAME + " · sing-box", 11, "#59657B"), margin(top = 18))
    }

    private fun loadNodes() {
        refreshButton.isEnabled = false
        chooseButton.isEnabled = false
        statusTitle.text = "Обновляю список…"
        statusDetail.text = "Загружаю свежие серверы FreeWave."
        executor.execute {
            val downloaded = runCatching { fetchNodes() }.getOrDefault(emptyList())
            runOnUiThread {
                nodes = downloaded
                countText.text = if (nodes.isEmpty()) "Рабочих конфигураций пока не найдено"
                    else nodes.size.toString() + " серверов в списке · проверим на этом телефоне"
                if (preferredNode != null && nodes.none { it.uri == preferredNode?.uri }) {
                    preferredNode = null
                    serverText.text = "Автоматически · лучший доступный"
                }
                refreshButton.isEnabled = true
                chooseButton.isEnabled = nodes.isNotEmpty()
                if (!connecting && !connected) {
                    statusTitle.text = if (nodes.isEmpty()) "Нет доступных серверов" else "Готов к подключению"
                    statusDetail.text = if (nodes.isEmpty()) "Проверь интернет и нажми «Обновить»."
                        else "Подготовлено " + nodes.size + " конфигураций. Автовыбор проверит соединение."
                }
            }
        }
    }
    private fun fetchNodes(): List<Node> {
        val fast = downloadFeed("freewave-fast.txt")
        val text = if (fast.isNotBlank()) fast else downloadFeed("freewave-vless.txt")
        val result = linkedMapOf<String, Node>()
        for (line in text.lineSequence()) {
            val raw = line.trim()
            if (!raw.startsWith("vless://") || raw.length > 16384) continue
            runCatching {
                val uri = android.net.Uri.parse(raw)
                val host = uri.host ?: return@runCatching
                val port = uri.port.takeIf { it > 0 } ?: 443
                val name = uri.fragment?.takeIf { it.isNotBlank() } ?: host
                result.putIfAbsent(raw, Node(raw, name, host, port))
            }
            if (result.size >= MAX_NODES) break
        }
        return result.values.toList()
    }
    private fun downloadFeed(file: String): String {
        val connection = URL(BASE_URL + file).openConnection() as HttpURLConnection
        connection.connectTimeout = 10000
        connection.readTimeout = 12000
        connection.setRequestProperty("User-Agent", "FreeWaveVPN-Android/" + BuildConfig.VERSION_NAME)
        connection.setRequestProperty("Cache-Control", "no-cache")
        try {
            if (connection.responseCode !in 200..299) return ""
            val output = StringBuilder()
            BufferedReader(InputStreamReader(connection.inputStream, Charsets.UTF_8)).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append('\n')
                    if (output.length > 2000000) break
                }
            }
            return output.toString()
        } finally { connection.disconnect() }
    }

    private fun requestVpnPermission() {
        if (nodes.isEmpty()) { loadNodes(); return }
        val permission = VpnService.prepare(this)
        if (permission != null) startActivityForResult(permission, VPN_REQUEST) else beginAutoConnect()
    }
    @Deprecated("Deprecated by Android; kept for broad API compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VPN_REQUEST) {
            if (resultCode == RESULT_OK) beginAutoConnect()
            else {
                statusTitle.text = "VPN не разрешён"
                statusDetail.text = "Android не разрешил приложению создать VPN-подключение."
            }
        }
    }

    private fun beginAutoConnect() {
        if (connecting) return
        if (nodes.isEmpty()) {
            loadNodes()
            return
        }
        connecting = true
        connected = false
        coreReady = false
        coreFailure = null
        connectButton.isEnabled = false
        statusTitle.text = "Ищу рабочий сервер…"
        statusDetail.text = "Проверяю до " + MAX_NODES + " конфигураций и жду фактического запуска sing-box."
        val orderedCandidates = preferredNode?.let { selected ->
            listOf(selected) + nodes.filterNot { it.uri == selected.uri }
        } ?: nodes
        val candidates = orderedCandidates.take(MAX_NODES)
        executor.execute {
            var working: Node? = null
            var lastFailure = "Не получен ответ от VPN-ядра"
            var tested = 0
            for ((index, node) in candidates.withIndex()) {
                if (Thread.currentThread().isInterrupted) break
                tested = index + 1
                coreReady = false
                coreFailure = null
                runOnUiThread {
                    statusTitle.text = "Проверяю сервер $tested из " + candidates.size
                    statusDetail.text = node.name + " · " + node.host + ":" + node.port
                }
                try {
                    getSharedPreferences("vpn_diagnostics", MODE_PRIVATE).edit().putString("recent", "").apply()
                    startNode(node.uri)
                    if (waitForCoreReady(7000)) {
                        val probeFailure = waitForInternet()
                        if (probeFailure == null) {
                            working = node
                            break
                        }
                        val coreLog = getSharedPreferences("vpn_diagnostics", MODE_PRIVATE)
                            .getString("recent", "").orEmpty().trim()
                        lastFailure = if (coreLog.isNotBlank()) {
                            (probeFailure + " | sing-box: " + coreLog.takeLast(420)).take(900)
                        } else probeFailure
                    } else {
                        lastFailure = coreFailure ?: "Ядро не подтвердило запуск за 7 секунд"
                    }
                } catch (error: Exception) {
                    lastFailure = error.message?.take(140) ?: "Ошибка запуска сервиса"
                }
                stopNode()
                try { Thread.sleep(650) } catch (_: InterruptedException) { break }
            }
            runOnUiThread {
                connecting = false
                connectButton.isEnabled = true
                if (working != null) {
                    connected = true
                    preferredNode = working
                    serverText.text = working!!.name
                    statusTitle.text = "Подключено"
                    statusDetail.text = "HTTPS-проверка прошла через " + working!!.host
                    connectButton.text = "Отключить VPN"
                } else {
                    connected = false
                    statusTitle.text = "Не удалось подключиться"
                    statusDetail.text = "Проверено $tested из " + candidates.size +
                        " серверов. Последняя причина: " + lastFailure
                    connectButton.text = "Повторить поиск"
                }
            }
        }
    }

    private fun startNode(uri: String) {
        val intent = Intent(this, FreeWaveVpnService::class.java)
            .setAction(FreeWaveVpnService.ACTION_START)
            .putExtra(FreeWaveVpnService.EXTRA_URI, uri)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent) else startService(intent)
    }
    private fun stopNode() {
        startService(Intent(this, FreeWaveVpnService::class.java).setAction(FreeWaveVpnService.ACTION_STOP))
    }
    private fun waitForCoreReady(timeoutMs: Long): Boolean {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (System.currentTimeMillis() < deadline && !Thread.currentThread().isInterrupted) {
            if (coreReady) return true
            if (coreFailure != null) return false
            try { Thread.sleep(100) } catch (_: InterruptedException) { return false }
        }
        return coreReady
    }

    // Returns null on success; otherwise keeps the concrete failure for the UI.
    private fun waitForInternet(): String? {
        try { Thread.sleep(350) } catch (_: InterruptedException) { return "Проверка прервана" }
        if (Thread.currentThread().isInterrupted) return "Проверка прервана"
        // probeThroughTunnel already performs layered TCP, DNS and HTTPS checks.
        return probeThroughTunnel()
    }

    private fun socksConnectDiagnostic(target: String, port: Int): String? {
        try {
            java.net.Socket().use { socket ->
                socket.connect(InetSocketAddress("127.0.0.1", 2080), 1000)
                socket.soTimeout = 2500
                val input = socket.getInputStream()
                val output = socket.getOutputStream()

                // SOCKS5 greeting: one supported method, no authentication.
                output.write(byteArrayOf(5, 1, 0))
                output.flush()
                val version = input.read()
                val method = input.read()
                if (version != 5 || method != 0) {
                    return "$target: SOCKS5 handshake rejected (version=$version, method=$method)"
                }

                val isIpv4 = target.matches(Regex("""\d{1,3}(?:\.\d{1,3}){3}"""))
                if (isIpv4) {
                    val address = target.split('.').map { it.toInt().toByte() }.toByteArray()
                    output.write(byteArrayOf(5, 1, 0, 1))
                    output.write(address)
                } else {
                    val address = target.toByteArray(Charsets.US_ASCII)
                    if (address.isEmpty() || address.size > 255) return "$target: invalid SOCKS hostname"
                    output.write(byteArrayOf(5, 1, 0, 3, address.size.toByte()))
                    output.write(address)
                }
                output.write((port shr 8) and 0xff)
                output.write(port and 0xff)
                output.flush()

                val responseVersion = input.read()
                val reply = input.read()
                input.read() // reserved
                input.read() // bound address type
                if (responseVersion != 5 || reply < 0) {
                    return "$target:$port: invalid or incomplete SOCKS5 response"
                }
                if (reply != 0) {
                    val reason = when (reply) {
                        1 -> "общая ошибка прокси"
                        2 -> "запрещено политикой"
                        3 -> "сеть недоступна"
                        4 -> "узел недоступен"
                        5 -> "соединение отклонено"
                        6 -> "TTL истёк"
                        7 -> "команда не поддерживается"
                        8 -> "тип адреса не поддерживается"
                        else -> "неизвестный код"
                    }
                    val code = "0x" + reply.toString(16).uppercase().padStart(2, '0')
                    return "$target:$port → SOCKS5 $code ($reason)"
                }
            }
            return null
        } catch (error: Exception) {
            val detail = error.message?.takeIf { it.isNotBlank() } ?: error.javaClass.simpleName
            return "$target:$port → ${error.javaClass.simpleName}: ${detail.take(90)}"
        }
    }

    private fun probeThroughTunnel(): String? {
        // Verify the local SOCKS listener, then separate outbound TCP failure from DNS failure.
        try {
            java.net.Socket().use { socket ->
                socket.connect(InetSocketAddress("127.0.0.1", 2080), 1000)
            }
        } catch (error: Exception) {
            return "Локальный SOCKS 127.0.0.1:2080 недоступен: " +
                (error.message ?: error.javaClass.simpleName).take(100)
        }

        val ipFailure = socksConnectDiagnostic("1.1.1.1", 443)
        if (ipFailure != null) {
            return "Не проходит TCP через VLESS до внешнего IP (проверка без DNS): $ipFailure"
        }

        val dnsFailure = socksConnectDiagnostic("www.gstatic.com", 443)
        if (dnsFailure != null) {
            return "TCP через VLESS по IP работает, но подключение по доменному имени не проходит (вероятна проблема DNS): $dnsFailure"
        }

        val proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", 2080))
        val addresses = listOf(
            "https://www.gstatic.com/generate_204",
            "https://cp.cloudflare.com/generate_204",
            "https://example.com/"
        )
        val failures = mutableListOf<String>()
        for (address in addresses) {
            var connection: HttpURLConnection? = null
            try {
                connection = URL(address).openConnection(proxy) as HttpURLConnection
                connection.connectTimeout = 3500
                connection.readTimeout = 3500
                connection.instanceFollowRedirects = false
                val code = connection.responseCode
                if (code in 200..399) return null
                failures.add(URL(address).host + ": HTTP " + code)
            } catch (error: Exception) {
                val detail = error.message?.takeIf { it.isNotBlank() } ?: error.javaClass.simpleName
                failures.add(URL(address).host + ": " + error.javaClass.simpleName + " (" + detail.take(75) + ")")
            } finally { connection?.disconnect() }
        }
        return failures.joinToString("; ").take(220).ifBlank { "HTTPS-проверка не удалась" }
    }
    private fun disconnect() {
        stopNode()
        connected = false
        connecting = false
        connectButton.text = "Подключиться автоматически"
        statusTitle.text = "Отключено"
        statusDetail.text = "VPN-туннель остановлен."
    }
    private fun showServerPicker() {
        val options = arrayListOf("Автоматически · рейтинг FreeWave")
        options.addAll(nodes.map { it.name + " · " + it.host })
        AlertDialog.Builder(this).setTitle("Выбрать сервер")
            .setItems(options.toTypedArray()) { _, which ->
                if (which == 0) {
                    preferredNode = null
                    serverText.text = "Автоматически · лучший доступный"
                } else {
                    preferredNode = nodes[which - 1]
                    serverText.text = preferredNode?.name ?: "Автоматически"
                }
            }.setNegativeButton("Закрыть", null).show()
    }

    private fun card() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(dp(18), dp(18), dp(18), dp(18))
        background = rounded("#151E31", 22, "#26334A")
    }
    private fun text(value: String, size: Int, color: String, bold: Boolean = false) =
        TextView(this).apply {
            text = value
            textSize = size.toFloat()
            setTextColor(Color.parseColor(color))
            if (bold) setTypeface(typeface, Typeface.BOLD)
        }
    private fun smallButton(label: String, action: () -> Unit) = Button(this).apply {
        text = label
        isAllCaps = false
        textSize = 13f
        setTextColor(Color.parseColor("#DCE6F3"))
        background = rounded("#202C42", 14, "#35445E")
        setOnClickListener { action() }
    }
    private fun rounded(fill: String, radius: Int, stroke: String? = null) =
        GradientDrawable().apply {
            setColor(Color.parseColor(fill))
            cornerRadius = dp(radius).toFloat()
            if (stroke != null) setStroke(dp(1), Color.parseColor(stroke))
        }
    private fun margin(top: Int = 0, bottom: Int = 0) = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { topMargin = dp(top); bottomMargin = dp(bottom) }
    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
