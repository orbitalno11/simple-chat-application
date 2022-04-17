package com.example.simplechatapp.ui.chatroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechatapp.model.ui.Message
import com.example.simplechatapp.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatRoomViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val _messages: MutableLiveData<List<Message>> = MutableLiveData(listOf())
    val messages: LiveData<List<Message>>
        get() = _messages

    fun fetchChat(chatId: String) {
        viewModelScope.launch {
            val chatDetail = withContext(Dispatchers.IO) {
                repository.getChat(chatId)
            }
            _messages.value = chatDetail.messages
        }
    }
}