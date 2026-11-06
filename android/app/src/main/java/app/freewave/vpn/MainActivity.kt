package app.freewave.vpn

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.ClipboardManager
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
import android.widget.EditText
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
    private data class Node(val uri: String, val name: String, val host: String, val port: Int, val latencyMs: Long? = null)
    companion object {
        private const val VPN_REQUEST = 4107
        private const val BASE_URL = "https://raw.githubusercontent.com/danyop12/FreeWaveVPN/main/public/"
        private const val MAX_NODES = 50
        private const val MAX_RANK_CANDIDATES = 12
        private const val MAX_CONNECT_ATTEMPTS = 3
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
        preferredNode = getSharedPreferences("freewave", MODE_PRIVATE)
            .getString("preferred_uri", null)?.let { saved ->
                runCatching {
                    val parsed = android.net.Uri.parse(saved)
                    Node(saved, parsed.fragment?.ifBlank { null } ?: parsed.host.orEmpty(),
                        parsed.host.orEmpty(), parsed.port.takeIf { it > 0 } ?: 443)
                }.getOrNull()
            }
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
            setPadding(dp(20), dp(22), dp(20), dp(24))
            setBackgroundColor(Color.rgb(8, 13, 27))
        }
        setContentView(ScrollView(this).apply { isFillViewport = true; addView(page) })

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        header.addView(ImageView(this).apply {
            setImageResource(R.drawable.ic_launcher_foreground)
            scaleType = ImageView.ScaleType.FIT_CENTER
            contentDescription = "FreeWave VPN"
        }, LinearLayout.LayoutParams(dp(48), dp(48)).apply { marginEnd = dp(12) })
        val brand = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(text("FREEWAVE", 17, "#F4F7FB", true))
            addView(text("БЕСПЛАТНЫЙ VPN", 10, "#55D6BE", true), margin(top = 2))
        }
        header.addView(brand)
        header.addView(TextView(this).apply {
            text = "v" + BuildConfig.VERSION_NAME
            textSize = 11f
            setTextColor(Color.parseColor("#91A0B8"))
            gravity = Gravity.CENTER
            background = rounded("#172238", 12)
            setPadding(dp(10), dp(7), dp(10), dp(7))
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_VERTICAL
            marginStart = dp(8)
        })
        page.addView(header, margin(bottom = 28))
        page.addView(text("Твой интернет.\nБез лишних сложностей.", 27, "#F4F7FB", true))
        page.addView(text("Подберём доступный публичный сервер и проверим реальное соединение.", 14, "#9EABC0"), margin(top = 9, bottom = 22))

        val statusCard = card().apply {
            setPadding(dp(20), dp(22), dp(20), dp(20))
            background = rounded("#121E33", 26, "#263B55")
        }
        val statusRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        statusRow.addView(text("СОЕДИНЕНИЕ", 11, "#91A0B8", true))
        statusRow.addView(TextView(this).apply {
            text = "● БЕСПЛАТНО"
            textSize = 10f
            setTextColor(Color.parseColor("#55D6BE"))
            gravity = Gravity.CENTER
            background = rounded("#173A3A", 20)
            setPadding(dp(10), dp(6), dp(10), dp(6))
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.END
            weight = 1f
        })
        statusCard.addView(statusRow)
        statusTitle = text("Загружаю серверы…", 23, "#F4F7FB", true)
        statusDetail = text("Получаю свежий список доступных узлов.", 13, "#9EABC0")
        statusCard.addView(statusTitle, margin(top = 18))
        statusCard.addView(statusDetail, margin(top = 7))
        connectButton = Button(this).apply {
            text = "Подключиться"
            isAllCaps = false
            textSize = 16f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.rgb(7, 24, 27))
            background = rounded("#55D6BE", 18)
            minHeight = dp(56)
            setOnClickListener { if (connected) disconnect() else requestVpnPermission() }
        }
        statusCard.addView(connectButton, margin(top = 22))
        page.addView(statusCard, margin(bottom = 16))

        val serverCard = card()
        val serverHeader = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        serverHeader.addView(text("СЕРВЕР", 11, "#91A0B8", true))
        serverHeader.addView(TextView(this).apply {
            text = "АВТОВЫБОР"
            textSize = 9f
            setTextColor(Color.parseColor("#55D6BE"))
            gravity = Gravity.CENTER
            background = rounded("#173A3A", 20)
            setPadding(dp(9), dp(5), dp(9), dp(5))
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.END
            weight = 1f
        })
        serverCard.addView(serverHeader)
        serverText = text("Лучший доступный", 17, "#F4F7FB", true)
        countText = text("Список серверов загружается…", 12, "#9EABC0")
        serverCard.addView(serverText, margin(top = 13))
        serverCard.addView(countText, margin(top = 5))
        val actions = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        refreshButton = smallButton("Обновить список") { loadNodes() }
        chooseButton = smallButton("Выбрать сервер") { showServerPicker() }
        actions.addView(refreshButton, LinearLayout.LayoutParams(0, dp(48), 1f).apply { marginEnd = dp(5) })
        actions.addView(chooseButton, LinearLayout.LayoutParams(0, dp(48), 1f).apply { marginStart = dp(5) })
        serverCard.addView(actions, margin(top = 16))
        serverCard.addView(smallButton("Импортировать VLESS из буфера") { showImportDialog() }, margin(top = 9))
        page.addView(serverCard, margin(bottom = 16))

        val explainCard = card()
        explainCard.addView(text("КАК ЭТО РАБОТАЕТ", 11, "#91A0B8", true))
        explainCard.addView(text("1. Получаем список публичных VLESS-серверов.\n2. Проверяем каждый кандидат через VPN-туннель.\n3. Подключаемся к первому узлу, который действительно пропускает интернет.", 13, "#C4CEDD"), margin(top = 12))
        page.addView(explainCard, margin(bottom = 14))
        page.addView(text("Важно: серверы принадлежат третьим лицам. Они могут отключаться, быть медленными или ненадёжными. Не используй их для банковских операций, паролей и чувствительных данных. FreeWave не гарантирует анонимность или удаление рекламы в YouTube.", 11, "#75839A"))
        page.addView(text("FreeWave VPN · sing-box", 11, "#56647B"), margin(top = 18))
    }

    private fun loadNodes() {
        val prefs = getSharedPreferences("freewave", MODE_PRIVATE)
        val cached = prefs.getString("cached_nodes", "").orEmpty()
            .lineSequence().mapNotNull(::parseNodeUri).toList()
        val custom = prefs.getStringSet("custom_nodes", emptySet()).orEmpty()
            .mapNotNull(::parseNodeUri)
        if (cached.isNotEmpty() || custom.isNotEmpty()) {
            nodes = (cached + custom).distinctBy { it.uri }
            countText.text = "${nodes.size} серверов · сохранённый список"
            chooseButton.isEnabled = true
            if (!connecting && !connected) {
                statusTitle.text = "Список готов"
                statusDetail.text = "Использую сохранённые серверы, пока обновляю каталог."
            }
        }
        refreshButton.isEnabled = false
        chooseButton.isEnabled = nodes.isNotEmpty()
        if (cached.isEmpty() && custom.isEmpty()) {
            statusTitle.text = "Обновляю список…"
            statusDetail.text = "Загружаю свежие серверы FreeWave."
        }
        executor.execute {
            val downloaded = runCatching { fetchNodes() }.getOrDefault(emptyList())
            runOnUiThread {
                val latestCustom = getSharedPreferences("freewave", MODE_PRIVATE)
                    .getStringSet("custom_nodes", emptySet()).orEmpty().mapNotNull(::parseNodeUri)
                nodes = (downloaded + latestCustom).distinctBy { it.uri }
                if (downloaded.isNotEmpty()) {
                    getSharedPreferences("freewave", MODE_PRIVATE).edit()
                        .putString("cached_nodes", downloaded.joinToString("\n") { it.uri }).apply()
                }
                countText.text = if (nodes.isEmpty()) "Рабочих конфигураций пока не найдено"
                    else nodes.size.toString() + " серверов в списке · проверим на этом телефоне"
                if (preferredNode != null && nodes.none { it.uri == preferredNode?.uri }) {
                    preferredNode = null
                    getSharedPreferences("freewave", MODE_PRIVATE).edit().remove("preferred_uri").apply()
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
        // Read every feed before applying the cap. The previous early break
        // stopped after fast + EU and never reached best/full fallback feeds.
        val feedNames = listOf(
            "freewave-fast.txt",
            "freewave-eu.txt",
            "freewave-best.txt",
            "freewave-vless.txt"
        )
        val parsedFeeds = feedNames.map { feed ->
            val parsed = linkedMapOf<String, Node>()
            val body = runCatching { downloadFeed(feed) }.getOrDefault("")
            for (line in body.lineSequence()) {
                val raw = line.trim()
                if (!raw.startsWith("vless://") || raw.length > 16384) continue
                runCatching {
                    val uri = android.net.Uri.parse(raw)
                    val host = uri.host ?: return@runCatching
                    val port = uri.port.takeIf { it in 1..65535 } ?: 443
                    val name = uri.fragment?.takeIf { it.isNotBlank() } ?: host
                    parsed.putIfAbsent(raw, Node(raw, name, host, port))
                }
            }
            parsed.values.toList()
        }

        // Round-robin sources so the first feed cannot monopolize all slots.
        val result = linkedMapOf<String, Node>()
        val maxRows = parsedFeeds.maxOfOrNull { it.size } ?: 0
        for (row in 0 until maxRows) {
            for (feed in parsedFeeds) {
                val node = feed.getOrNull(row) ?: continue
                result.putIfAbsent(node.uri, node)
                if (result.size >= MAX_NODES) return result.values.toList()
            }
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

    private fun parseNodeUri(rawValue: String): Node? {
        val raw = rawValue.trim()
        if (!raw.startsWith("vless://") || raw.length > 16384) return null
        return runCatching {
            val parsed = android.net.Uri.parse(raw)
            val host = parsed.host ?: return@runCatching null
            val port = parsed.port.takeIf { it in 1..65535 } ?: 443
            val name = parsed.fragment?.takeIf { it.isNotBlank() } ?: host
            Node(raw, name, host, port)
        }.getOrNull()
    }

    private fun showImportDialog() {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val pasted = runCatching {
            clipboard.primaryClip?.getItemAt(0)?.coerceToText(this)?.toString().orEmpty()
        }.getOrDefault("")
        val field = EditText(this).apply {
            hint = "vless://…"
            minLines = 2
            setText(pasted.takeIf { it.trim().startsWith("vless://") }.orEmpty())
        }
        val wrapper = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(8), dp(20), 0)
            addView(field)
        }
        AlertDialog.Builder(this).setTitle("Импорт VLESS")
            .setMessage("Вставь ссылку из буфера или введи её вручную.")
            .setView(wrapper)
            .setPositiveButton("Добавить") { _, _ ->
                val node = parseNodeUri(field.text.toString())
                if (node == null) {
                    statusDetail.text = "Не удалось прочитать ссылку. Нужна корректная vless:// конфигурация."
                } else {
                    val prefs = getSharedPreferences("freewave", MODE_PRIVATE)
                    val custom = prefs.getStringSet("custom_nodes", emptySet()).orEmpty().toMutableSet()
                    custom.add(node.uri)
                    prefs.edit().putStringSet("custom_nodes", custom)
                        .putString("preferred_uri", node.uri).apply()
                    nodes = (listOf(node) + nodes).distinctBy { it.uri }
                    preferredNode = node
                    serverText.text = node.name
                    countText.text = "${nodes.size} серверов · добавлен свой сервер"
                    chooseButton.isEnabled = true
                    statusDetail.text = "Сервер добавлен и выбран. Нажми «Подключиться» для проверки."
                }
            }.setNegativeButton("Отмена", null).show()
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
        statusDetail.text = "Сначала измерю доступность серверов, затем проверю VPN-туннель."
        val preferred = preferredNode
        // The feed is pre-ranked server-side. Re-rank only a small shortlist on-device.
        val sourceCandidates = (preferred?.let { selected ->
            listOf(selected) + nodes.filterNot { it.uri == selected.uri }
        } ?: nodes).take(MAX_RANK_CANDIDATES)
        executor.execute {
            val ranked = rankCandidates(sourceCandidates)
            val orderedCandidates = if (preferred != null) {
                listOfNotNull(ranked.firstOrNull { it.uri == preferred.uri }) +
                    ranked.filterNot { it.uri == preferred.uri }
            } else ranked
            val candidates = orderedCandidates.take(MAX_CONNECT_ATTEMPTS)
            runOnUiThread {
                countText.text = if (ranked.isEmpty()) "Нет доступных TCP-узлов"
                    else "Оценено ${ranked.size} серверов · сначала быстрые"
                statusDetail.text = "Проверяю соединение через лучшие доступные узлы…"
            }
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
                    statusDetail.text = node.name + " · " + (node.latencyMs?.let { "$it мс до узла" } ?: "TCP-проверка не пройдена")
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
                    getSharedPreferences("freewave", MODE_PRIVATE).edit()
                        .putString("preferred_uri", working!!.uri).apply()
                    serverText.text = working!!.name
                    statusTitle.text = "Подключено"
                    statusDetail.text = "Интернет проверен через " + working!!.host + (working!!.latencyMs?.let { " · TCP $it мс" } ?: "")
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

    // TCP reachability is only a first-pass ranking signal, not a real VPN latency test.
    // Every candidate is still verified through sing-box before it can be marked connected.
    private fun rankCandidates(candidates: List<Node>): List<Node> {
        if (candidates.size < 2) return candidates
        val pool = Executors.newFixedThreadPool(8)
        try {
            val futures = pool.invokeAll(candidates.map { node ->
                java.util.concurrent.Callable {
                    val started = System.nanoTime()
                    val latency = runCatching {
                        java.net.Socket().use { socket ->
                            socket.connect(InetSocketAddress(node.host, node.port), 1400)
                        }
                        ((System.nanoTime() - started) / 1_000_000L).coerceAtLeast(1L)
                    }.getOrNull()
                    node.copy(latencyMs = latency)
                }
            }, 9, java.util.concurrent.TimeUnit.SECONDS)
            return futures.mapNotNull { future ->
                runCatching { if (future.isDone && !future.isCancelled) future.get() else null }.getOrNull()
            }.sortedWith(compareBy<Node> { it.latencyMs == null }.thenBy { it.latencyMs ?: Long.MAX_VALUE })
        } finally {
            pool.shutdownNow()
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

    private fun probeThroughTunnel(): String? {
        // A local listener only proves that sing-box started, not that VLESS works.
        // Verify real HTTPS requests (including TLS SNI) through SOCKS instead.
        try {
            java.net.Socket().use { socket ->
                socket.connect(InetSocketAddress("127.0.0.1", 2080), 1500)
            }
        } catch (error: Exception) {
            return "Локальный SOCKS 127.0.0.1:2080 недоступен: " +
                (error.message ?: error.javaClass.simpleName).take(100)
        }

        val proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", 2080))
        val addresses = listOf(
            "https://www.gstatic.com/generate_204",
            "https://www.google.com/generate_204",
            "https://github.com/robots.txt",
            "https://example.com/"
        )
        val failures = mutableListOf<String>()
        for (address in addresses) {
            var connection: HttpURLConnection? = null
            try {
                connection = URL(address).openConnection(proxy) as HttpURLConnection
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                connection.instanceFollowRedirects = false
                connection.requestMethod = "GET"
                connection.setRequestProperty("User-Agent", "FreeWaveVPN-Android/" + BuildConfig.VERSION_NAME)
                val code = connection.responseCode
                // Any valid HTTP response proves that TCP + TLS traversed the VLESS node.
                if (code in 100..599) return null
                failures.add(URL(address).host + ": некорректный HTTP-ответ " + code)
            } catch (error: Exception) {
                val detail = error.message?.takeIf { it.isNotBlank() } ?: error.javaClass.simpleName
                failures.add(URL(address).host + ": " + error.javaClass.simpleName + " (" + detail.take(85) + ")")
            } finally {
                connection?.disconnect()
            }
        }

        // A raw SOCKS CONNECT to 1.1.1.1 with no TLS/SNI is deliberately not
        // used as a pass/fail condition: public nodes may apply destination rules.
        return "Не удалось получить HTTPS-ответ через VLESS ни от одной проверки: " +
            failures.joinToString("; ").take(420)
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
        val prefs = getSharedPreferences("freewave", MODE_PRIVATE)
        val favorites = prefs.getStringSet("favorite_uris", emptySet()).orEmpty()
        val sorted = nodes.sortedWith(compareByDescending<Node> { it.uri in favorites }
            .thenBy { it.latencyMs ?: Long.MAX_VALUE })
        val options = arrayListOf("Автоматически · рейтинг FreeWave")
        options.addAll(sorted.map { node ->
            (if (node.uri in favorites) "★ " else "") + node.name + " · " + node.host +
                (node.latencyMs?.let { " · $it мс" } ?: " · не проверен")
        })
        AlertDialog.Builder(this).setTitle("Серверы")
            .setItems(options.toTypedArray()) { _, which ->
                if (which == 0) {
                    preferredNode = null
                    prefs.edit().remove("preferred_uri").apply()
                    serverText.text = "Автоматически · лучший доступный"
                } else {
                    preferredNode = sorted[which - 1]
                    prefs.edit().putString("preferred_uri", preferredNode?.uri).apply()
                    serverText.text = preferredNode?.name ?: "Автоматически"
                }
            }.setNeutralButton("Избранное") { _, _ -> showFavoritesDialog(sorted) }
            .setNegativeButton("Закрыть", null).show()
    }

    private fun showFavoritesDialog(source: List<Node>) {
        val prefs = getSharedPreferences("freewave", MODE_PRIVATE)
        val selected = prefs.getStringSet("favorite_uris", emptySet()).orEmpty().toMutableSet()
        val labels = source.map { node -> node.name + " · " + node.host }.toTypedArray()
        val checked = source.map { it.uri in selected }.toBooleanArray()
        AlertDialog.Builder(this).setTitle("Избранные серверы")
            .setMultiChoiceItems(labels, checked) { _, index, isChecked ->
                if (isChecked) selected.add(source[index].uri) else selected.remove(source[index].uri)
                prefs.edit().putStringSet("favorite_uris", selected).apply()
            }
            .setPositiveButton("Готово", null).show()
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
