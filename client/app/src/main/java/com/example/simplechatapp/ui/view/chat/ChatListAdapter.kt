package com.example.simplechatapp.ui.view.chat

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplechatapp.model.ui.Chat

class ChatItemListAdapter(
    private val listener: ChatItemView.OnChatClickListener
) : RecyclerView.Adapter<ChatItemListAdapter.ChatItemViewHolder>() {

    private var chats: List<Chat> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val view = ChatItemView(parent.context)
        return ChatItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int = chats.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Chat>) {
        chats = list
        notifyDataSetChanged()
    }

    class ChatItemViewHolder(
        private val view: ChatItemView,
        private val listener: ChatItemView.OnChatClickListener
    ) : RecyclerView.ViewHolder(view.rootView) {
        fun bind(item: Chat) {
            view.binding.userNameTextView.text = item.name
            view.binding.userImageView.setImageURI(item.pictureUrl)
            view.binding.root.setOnClickListener {
                listener.onChatClick(item.transactionId)
            }
        }
    }
}