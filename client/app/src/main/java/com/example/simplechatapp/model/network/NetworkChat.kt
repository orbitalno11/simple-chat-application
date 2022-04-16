package com.example.simplechatapp.model.network

import com.google.gson.annotations.SerializedName

data class NetworkChat(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("picture")
    var pictureUrl: String = "",
    @SerializedName("participant_id")
    var participantId: String,
    @SerializedName("transaction_id")
    var transactionId: String,
    @SerializedName("is_favorite")
    var favorite: Boolean
)
