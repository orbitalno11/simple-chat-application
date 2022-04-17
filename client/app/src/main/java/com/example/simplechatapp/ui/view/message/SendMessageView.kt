package com.example.simplechatapp.ui.view.message

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.simplechatapp.databinding.ViewSendMessageItemBinding

class SendMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    val binding = ViewSendMessageItemBinding.inflate(LayoutInflater.from(context), this, true)
}
