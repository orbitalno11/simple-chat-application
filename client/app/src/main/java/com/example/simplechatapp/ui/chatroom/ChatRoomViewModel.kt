package com.example.simplechatapp.ui.chatroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechatapp.model.ui.Message
import com.example.simplechatapp.model.ui.form.SendMessageForm
import com.example.simplechatapp.network.SocketHandler
import com.example.simplechatapp.network.onReceivePrivateMessage
import com.example.simplechatapp.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ChatRoomViewModel : ViewModel() {

    private val repository = ChatRepository()

    private var chatId: String? = null

    private val _messages: MutableLiveData<List<Message>> = MutableLiveData(listOf())
    val messages: LiveData<List<Message>>
        get() = _messages

    init {
        SocketHandler.setSocket()
    }

    fun fetchChat(id: String) {
        viewModelScope.launch {
            val chatDetail = withContext(Dispatchers.IO) {
                repository.getChat(id)
            }
            _messages.value = chatDetail.messages
            chatId = id
        }
    }

    fun observeNewMessage(id: String) {
        SocketHandler.getSocket().onReceivePrivateMessage(id) {
            it ?: return@onReceivePrivateMessage
            val messages = _messages.value?.toMutableList() ?: return@onReceivePrivateMessage
            messages.add(it)
            _messages.postValue(messages)
        }
    }

    fun sendMessage(message: String) {
        chatId ?: return
        viewModelScope.launch {
            val form = SendMessageForm(
                message = message,
                sendTime = Date().time
            )
            repository.sendMessage(form, chatId!!)
        }
    }
}