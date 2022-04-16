package com.example.simplechatapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplechatapp.databinding.FragmentHomeBinding
import com.example.simplechatapp.ui.view.chat.ChatItemListAdapter
import com.example.simplechatapp.ui.view.chat.ChatItemView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: ChatItemListAdapter

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        adapter = ChatItemListAdapter(OnChatClickListener())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.chats.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }

    private inner class OnChatClickListener : ChatItemView.OnChatClickListener {
        override fun onChatClick(chatId: String) {
            Toast.makeText(requireContext(), "Click: $chatId", Toast.LENGTH_LONG).show()
        }
    }
}