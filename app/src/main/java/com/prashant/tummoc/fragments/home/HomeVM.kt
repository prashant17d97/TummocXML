package com.prashant.tummoc.fragments.home

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.prashant.tummoc.R
import com.prashant.tummoc.fragments.home.model.Routes
import com.prashant.tummoc.fragments.model.JsonFile.Companion.jsonFile
import com.prashant.tummoc.fragments.model.ResponseItem
import com.prashant.tummoc.genericadapters.RecyclerAdapter

class HomeVM :ViewModel() {

    val likeSource = ObservableField(false)
    val likeDestination = ObservableField(false)
    val source = ObservableField("")
    val destination = ObservableField("")
    val viewPagerAdapter = RecyclerAdapter<Routes>(R.layout.routes)
    private val metroRoutes = RecyclerAdapter<ResponseItem>(R.layout.routes_card)
    private val busRoutes = RecyclerAdapter<ResponseItem>(R.layout.routes_card)

    init {
        busRoutes.addItems(jsonFile.routeList)
        viewPagerAdapter.addItems(
            listOf(
                Routes(metroRoutes),
                Routes(busRoutes),
            )
        )
        metroRoutes.setOnItemClick { view, _ ->
            when (view.id) {
                R.id.clRoute -> {
                    view.findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
                }
            }
        }
        busRoutes.setOnItemClick { view, _ ->
            when (view.id) {
                R.id.clRoute -> {
                    view.findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
                }
            }
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.ivLikeDestination -> likeDestination.set(!(likeDestination.get() ?: false))
            R.id.ivLikeSource -> likeSource.set(!(likeSource.get() ?: false))
        }
    }
}
