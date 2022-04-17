package com.example.simplechatapp.ui.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplechatapp.databinding.FragmentChatroomBinding
import com.example.simplechatapp.ui.view.message.MessageAdapter

class ChatRoomFragment : Fragment() {

    private var _binding: FragmentChatroomBinding? = null
    private val binding: FragmentChatroomBinding
        get() = _binding!!

    private lateinit var viewModel: ChatRoomViewModel

    private lateinit var adapter: MessageAdapter

    companion object {
        fun create(chatId: String?): ChatRoomFragment {
            val bundle = Bundle()
            bundle.putString("chatId", chatId)
            return ChatRoomFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatroomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chatId = arguments?.getString("chatId")

        viewModel = ViewModelProvider(requireActivity()).get(ChatRoomViewModel::class.java)

        chatId?.let {
            viewModel.fetchChat(it)
        }

        setupView()
        observeViewModel()
    }

    private fun setupView() {
        adapter = MessageAdapter()
        binding.messageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.messageRecyclerView.adapter = adapter

        binding.sendMessageButton.setOnClickListener {
            // TODO implement send message
        }
    }

    private fun observeViewModel() {
        viewModel.messages.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }
}