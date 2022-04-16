package com.example.simplechatapp.model.network

import com.google.gson.annotations.SerializedName

data class NetworkMessage(
    @SerializedName("sender_id")
    var sender: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("send_time")
    var sendTime: Long
)
