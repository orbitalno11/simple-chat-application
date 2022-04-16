package com.example.simplechatapp.model.network

import com.google.gson.annotations.SerializedName

data class NetworkChatDetail(
    @SerializedName("participant")
    var participant: NetworkParticipant,
    @SerializedName("messages")
    var messages: List<NetworkMessage>
) {
    data class NetworkParticipant(
        @SerializedName("name")
        var name: String
    )
}
