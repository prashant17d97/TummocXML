package com.prashant.tummoc.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.prashant.tummoc.R
import com.prashant.tummoc.databinding.HomeBinding


class HomeFragment : Fragment() {
    private var _binding: HomeBinding? = null
    private var binding = _binding
    private val viewModel by viewModels<HomeVM>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomeBinding.inflate(layoutInflater)
        binding?.homeVM = viewModel
        uiConfiguration()
        return binding?.root
    }

    private fun uiConfiguration() {
        val tabLayout = binding?.tabs
        binding?.viewPager?.adapter = viewModel.viewPagerAdapter
        val viewPager2 = binding?.viewPager
        if (tabLayout != null && viewPager2 != null) {
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                when (position) {
                    0 -> tab.apply {
                        icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_metro)
                        text = "Metro"
                    }
                    1 -> tab.apply {
                        icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_bus)
                        text = "Bus"
                    }
                }
            }.attach()
        }
    }
}