package com.example.simplechatapp.network

import com.example.simplechatapp.BuildConfig
import com.example.simplechatapp.exception.SocketException
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.lang.Exception
import java.net.URISyntaxException

object SocketHandler {
    private const val socketUrl = BuildConfig.SERVER_URL

    private lateinit var mSocket: Socket

    fun setSocket() {
        try {
            mSocket = IO.socket(socketUrl)
        } catch (e: URISyntaxException) {
            throw SocketException(e.message ?: "UNKNOWN")
        }
    }

    fun getSocket(): Socket = mSocket
}

enum class SocketEvent(val value: String) {
    PRIVATE_MESSAGE("private-message"),
    PUBLIC_MESSAGE("public-message")
}

fun Socket.onReceivePublicMessage(listener: (message: String?) -> Unit) {
    this.connect()
    this.on(SocketEvent.PUBLIC_MESSAGE.value) {
        try {
            val data = it[0] as JSONObject
            val message = data.getString("message")
            listener(message)
        } catch (e: Exception) {
            listener(null)
        }
    }
}

fun Socket.onReceivePrivateMessage(listener: (message: String?) -> Unit) {
    this.connect()
    this.on(SocketEvent.PRIVATE_MESSAGE.value) {
        try {
            val data = it[0] as JSONObject
            val message = data.getString("message")
            listener(message)
        } catch (e: Exception) {
            listener(null)
        }
    }
}