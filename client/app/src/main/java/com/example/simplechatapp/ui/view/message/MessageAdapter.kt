package com.example.simplechatapp.ui.view.message

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplechatapp.databinding.ViewMessageItemBinding
import com.example.simplechatapp.model.ui.Message

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageItemViewHolder>() {

    private var messages: List<Message> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemViewHolder {
        val binding =
            ViewMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageItemViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Message>) {
        messages = list
        notifyDataSetChanged()
    }

    class MessageItemViewHolder(private val view: ViewMessageItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: Message) {
            if (item.senderId == "user_id_2") {
                view.root.displayedChild = 0
                view.sendMessageView.binding.messageTextView.text = item.message
            } else {
                view.root.displayedChild = 1
                view.receiveMessageView.binding.messageTextView.text = item.message
            }
        }
    }
}