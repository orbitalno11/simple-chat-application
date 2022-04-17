package com.example.simplechatapp.ui.view.message

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.simplechatapp.databinding.ViewReceiveMessageItemBinding

class ReceiveMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    val binding = ViewReceiveMessageItemBinding.inflate(LayoutInflater.from(context), this, true)
}
