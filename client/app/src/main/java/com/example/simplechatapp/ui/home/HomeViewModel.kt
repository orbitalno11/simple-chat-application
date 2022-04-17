package com.example.simplechatapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechatapp.model.ui.Chat
import com.example.simplechatapp.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val _chats: MutableLiveData<List<Chat>> = MutableLiveData(listOf())
    val chats: LiveData<List<Chat>>
        get() = _chats

    init {
        viewModelScope.launch {
            _chats.value = withContext(Dispatchers.IO) {
                repository.getChats()
            }
        }
    }
}
