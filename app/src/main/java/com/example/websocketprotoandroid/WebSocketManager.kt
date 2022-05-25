package com.example.websocketprotoandroid

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI
import java.util.concurrent.TimeUnit

class WebSocketManager () {

    companion object{
        const val WEB_SOCKET_URL = "ws://foodgiver.hopto.org:8080/chat"
        const val TAG = "Foodgiver"
        var connected : Boolean = false
        const val timeout : Long = 3000
        var mClient : WsClient? = null
        class WsClient : WebSocketClient(URI(WEB_SOCKET_URL)){
            override fun onOpen(handshakeData: ServerHandshake?) {
                Log.d(TAG, "onOpen was called")
                connected = true
                subscribe()
            }

            override fun onMessage(message: String){
                Log.d(TAG, "Message from server: " + message)
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.d(TAG,"onClose called, code: " + code + " reason: " + reason)
                connected = false
            }

            override fun onError(ex: Exception?) {
                Log.d(TAG, "Error, throw exception here")
            }

            private fun subscribe(){
                // Subscribe to foodgiver service to receive notifications
                send("auth_420\n")
            }
        }
        fun connectClient(){
            if(!connected){
                mClient = WsClient()
                mClient?.connectBlocking(timeout, TimeUnit.MILLISECONDS)
            }
        }
    }
}