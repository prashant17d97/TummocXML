package com.prashant.tummoc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.prashant.tummoc.R
import com.prashant.tummoc.databinding.ActivityMainBinding

class MainActivity :AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private var binding = _binding
    private val viewModel by viewModels<MainVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}