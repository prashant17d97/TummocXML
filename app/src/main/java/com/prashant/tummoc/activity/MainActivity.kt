package com.prashant.tummoc.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.prashant.tummoc.databinding.ActivityMainBinding
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private var binding = _binding
    private val viewModel by viewModels<MainVM>()

    companion object {
        lateinit var weakReference: WeakReference<MainActivity>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.viewModel = viewModel
        weakReference = WeakReference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        weakReference = WeakReference(null)
        _binding = null
    }
}