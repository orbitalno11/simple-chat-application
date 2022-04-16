package com.example.simplechatapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.simplechatapp.R
import com.example.simplechatapp.databinding.ActivityHolderBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHolderBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragmentContainerView, HomeFragment.create())
            }
        }
    }

}