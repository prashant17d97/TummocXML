package com.prashant.tummoc.fragments.home.model

import com.prashant.tummoc.fragments.model.ResponseItem
import com.prashant.tummoc.genericadapters.AbstractModel
import com.prashant.tummoc.genericadapters.RecyclerAdapter

data class Routes(
    val routeAdapter: RecyclerAdapter<ResponseItem>
) :AbstractModel() {
    val isRouteAvailable: Boolean
        get() = routeAdapter.getAllItems().isNotEmpty()
}
