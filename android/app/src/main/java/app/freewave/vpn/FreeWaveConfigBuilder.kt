package app.freewave.vpn

import android.net.Uri
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

object FreeWaveConfigBuilder {
    fun build(uriText: String): String {
        require(uriText.length <= 16384) { "Ссылка слишком длинная" }
        val uri = Uri.parse(uriText.trim())
        require(uri.scheme.equals("vless", ignoreCase = true)) { "Поддерживаются только VLESS-ссылки" }
        val uuid = uri.userInfo?.trim().orEmpty()
        require(runCatching { UUID.fromString(uuid) }.isSuccess) { "Некорректный UUID узла" }
        val host = uri.host?.trim().orEmpty()
        require(host.isNotBlank()) { "В ссылке не указан адрес сервера" }
        val port = uri.port.takeIf { it in 1..65535 } ?: 443
        val query = mutableMapOf<String, String>()
        for (key in uri.queryParameterNames) query[key.lowercase()] = uri.getQueryParameter(key).orEmpty()
        fun value(vararg keys: String, default: String = ""): String {
            for (key in keys) {
                val found = query[key.lowercase()].orEmpty()
                if (found.isNotBlank()) return found
            }
            return default
        }
        val security = value("security", default = "tls").lowercase()
        require(security == "tls" || security == "reality") { "Узел без TLS/REALITY не поддерживается" }
        val outbound = JSONObject()
            .put("type", "vless")
            .put("tag", "proxy")
            .put("server", host)
            .put("server_port", port)
            .put("uuid", uuid)
        val flow = value("flow")
        if (flow.isNotBlank()) outbound.put("flow", flow)
        val tls = JSONObject()
            .put("enabled", true)
            .put("server_name", value("sni", "servername", default = host))
            .put("utls", JSONObject().put("enabled", true).put("fingerprint", value("fp", default = "chrome")))
        val alpn = value("alpn")
        if (alpn.isNotBlank()) {
            tls.put("alpn", JSONArray().apply {
                alpn.split(',').map(String::trim).filter(String::isNotBlank).forEach(::put)
            })
        }
        if (security == "reality") {
            val publicKey = value("pbk", "publickey")
            require(publicKey.isNotBlank()) { "В REALITY-ссылке отсутствует публичный ключ" }
            val reality = JSONObject().put("enabled", true).put("public_key", publicKey)
            val shortId = value("sid", "shortid")
            if (shortId.isNotBlank()) reality.put("short_id", shortId)
            tls.put("reality", reality)
        }
        outbound.put("tls", tls)
        val transportType = value("type", default = "tcp").lowercase()
        val path = value("path", default = "/")
        val transport = when (transportType) {
            "tcp", "raw", "" -> null
            "ws", "websocket" -> JSONObject().put("type", "ws").put("path", path).apply {
                val wsHost = value("host")
                if (wsHost.isNotBlank()) put("headers", JSONObject().put("Host", wsHost))
            }
            "grpc" -> JSONObject().put("type", "grpc").put("service_name", value("servicename", "service_name", "path"))
            "httpupgrade" -> JSONObject().put("type", "httpupgrade").put("path", path).apply {
                val wsHost = value("host")
                if (wsHost.isNotBlank()) put("headers", JSONObject().put("Host", wsHost))
            }
            "http", "xhttp", "splithttp" -> JSONObject().put("type", "http").put("path", path).apply {
                val wsHost = value("host")
                if (wsHost.isNotBlank()) put("host", JSONArray().put(wsHost))
                val mode = value("mode")
                if (mode.isNotBlank()) put("mode", mode)
            }
            else -> throw IllegalArgumentException("Неизвестный транспорт: " + transportType)
        }
        if (transport != null) outbound.put("transport", transport)
        val config = JSONObject()
            .put("log", JSONObject().put("level", "warn").put("timestamp", true))
            .put("dns", JSONObject()
                .put("servers", JSONArray().put(JSONObject()
                    .put("type", "https")
                    .put("tag", "remote-dns")
                    .put("server", "1.1.1.1")
                    .put("path", "/dns-query")
                    .put("tls", JSONObject().put("enabled", true).put("server_name", "cloudflare-dns.com"))
                    .put("detour", "proxy")))
                .put("final", "remote-dns")
                .put("strategy", "ipv4_only"))
            .put("inbounds", JSONArray()
                .put(JSONObject()
                    .put("type", "tun")
                    .put("tag", "tun-in")
                    .put("interface_name", "tun0")
                    .put("address", JSONArray().put("172.19.0.1/30"))
                    .put("mtu", 1400)
                    .put("auto_route", true)
                    .put("strict_route", true)
                    .put("stack", "mixed"))
                .put(JSONObject()
                    .put("type", "mixed")
                    .put("tag", "health-in")
                    .put("listen", "127.0.0.1")
                    .put("listen_port", 2080)))
            .put("outbounds", JSONArray()
                .put(outbound)
                .put(JSONObject().put("type", "direct").put("tag", "direct")))
            .put("route", JSONObject()
                .put("auto_detect_interface", true)
                .put("rules", JSONArray().put(JSONObject()
                    .put("protocol", "dns")
                    .put("action", "hijack-dns")))
                .put("final", "proxy"))
        return config.toString()
    }
}
