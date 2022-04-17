package com.example.simplechatapp.network

import com.example.simplechatapp.model.network.NetworkChat
import com.example.simplechatapp.model.network.NetworkChatDetail
import com.example.simplechatapp.model.network.NetworkResponse
import com.example.simplechatapp.model.network.form.NetworkSendMessageForm
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApi {
    @GET("chats?user=user_id_2")
    suspend fun getAllChat(): NetworkResponse<List<NetworkChat>>

    @GET("chats/{chatId}?user=user_id_2")
    suspend fun getChat(
        @Path("chatId") chatId: String
    ): NetworkResponse<NetworkChatDetail>

    @POST("chats/{chatId}?user=user_id_2")
    suspend fun sendMessage(
        @Path("chatId") chatId: String,
        @Body body: NetworkSendMessageForm
    )
}