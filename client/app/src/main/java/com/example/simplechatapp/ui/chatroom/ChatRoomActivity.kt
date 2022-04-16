package com.example.simplechatapp.ui.chatroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplechatapp.databinding.ActivityHolderBinding

class ChatRoomActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}