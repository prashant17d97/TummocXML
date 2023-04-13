package com.prashant.tummoc.genericadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/** Binding Adapters */
object BindingAdapters {

    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
    ) {
        recyclerView.adapter = adapter

    }

}