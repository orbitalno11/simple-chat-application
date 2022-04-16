package com.example.simplechatapp.repository

import com.example.simplechatapp.model.network.form.NetworkSendMessageForm
import com.example.simplechatapp.model.ui.Chat
import com.example.simplechatapp.model.ui.ChatDetail
import com.example.simplechatapp.model.ui.Message
import com.example.simplechatapp.model.ui.form.SendMessageForm
import com.example.simplechatapp.network.ChatApi
import com.example.simplechatapp.network.HttpBuilder

class ChatRepository {
    private val dataSource = HttpBuilder().createRetrofit().create(ChatApi::class.java)

    suspend fun getChats(): List<Chat> {
        val networkChats = dataSource.getAllChat()
        return networkChats.data.map { chat ->
            Chat(
                name = chat.name,
                pictureUrl = chat.pictureUrl,
                transactionId = chat.transactionId,
                favorite = chat.favorite
            )
        }
    }

    suspend fun getChat(id: String): ChatDetail {
        val networkChat = dataSource.getChat(id)
        val participant = networkChat.data.participant
        val messages = networkChat.data.messages
        return ChatDetail(
            participant = ChatDetail.Participant(participant.name),
            messages = messages.map { message ->
                Message(
                    senderId = message.sender,
                    senderName = participant.name,
                    message = message.message,
                    sendTime = message.sendTime
                )
            }
        )
    }

    suspend fun sendMessage(message: SendMessageForm) {
        val form = NetworkSendMessageForm(
            message = message.message,
            sendTime = message.sendTime
        )
        dataSource.sendMessage(form)
    }
}