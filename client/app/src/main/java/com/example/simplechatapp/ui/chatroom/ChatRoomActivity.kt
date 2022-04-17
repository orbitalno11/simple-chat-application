package com.example.simplechatapp.ui.chatroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.simplechatapp.R
import com.example.simplechatapp.databinding.ActivityHolderBinding

class ChatRoomActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHolderBinding
    private lateinit var viewModel: ChatRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ChatRoomViewModel::class.java)

        if (savedInstanceState == null) {
            val chatId = intent.extras?.getString("chatId")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragmentContainerView, ChatRoomFragment.create(chatId))
            }
        }
    }
}