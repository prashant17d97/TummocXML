package com.prashant.tummoc.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.prashant.tummoc.R
import com.prashant.tummoc.databinding.HomeBinding


class HomeFragment :Fragment() {
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

    /**

     *Configures the UI elements of the fragment.

     *Sets up the view pager with the view model's view pager adapter.

     *Sets up the tab layout and its mediator to handle tab selection and display.
     */
    private fun uiConfiguration() {
        val tabLayout = binding?.tabs
        binding?.viewPager?.adapter = viewModel.viewPagerAdapter
        val viewPager2 = binding?.viewPager

        // If tab layout and view pager are both not null, set up tab layout mediator
        if (tabLayout != null && viewPager2 != null) {
            // Set up the tab layout mediator with icons and text for each tab
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                when (position) {
                    0 -> tab.apply {
                        icon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_metro
                        ) // Set icon for Metro tab
                        text = "Metro" // Set text for Metro tab
                    }
                    1 -> tab.apply {
                        icon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_bus
                        ) // Set icon for Bus tab
                        text = "Bus" // Set text for Bus tab
                    }
                }
            }.attach() // Attach the tab layout mediator to the tab layout and view pager

        }
    }
}