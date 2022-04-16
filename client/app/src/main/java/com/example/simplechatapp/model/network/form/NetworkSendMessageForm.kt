package com.example.simplechatapp.model.network.form

import com.google.gson.annotations.SerializedName

data class NetworkSendMessageForm(
    @SerializedName("message")
    var message: String,
    @SerializedName("send_time")
    var sendTime: Long
)
