package com.example.simplechatapp.ui.view.chat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.simplechatapp.databinding.ViewChatItemBinding

class ChatItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    val binding = ViewChatItemBinding.inflate(LayoutInflater.from(context), this, true)

    interface OnChatClickListener {
        fun onChatClick(chatId: String)
    }
}