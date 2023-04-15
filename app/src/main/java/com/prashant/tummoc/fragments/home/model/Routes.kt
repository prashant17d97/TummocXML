package com.prashant.tummoc.fragments.home.model

import com.prashant.tummoc.fragments.model.ResponseItem
import com.prashant.tummoc.genericadapters.AbstractModel
import com.prashant.tummoc.genericadapters.RecyclerAdapter

/**

 *This data class represents the list of routes that can be displayed in the HomeFragment.
 *@param routeAdapter An instance of RecyclerAdapter<ResponseItem> that holds the list of routes.
 */
data class Routes(
    val routeAdapter: RecyclerAdapter<ResponseItem>
) :AbstractModel() {

    /**
     *This property returns true if there is at least one route available to display.
     */
    val isRouteAvailable: Boolean
        get() = routeAdapter.getAllItems().isNotEmpty()
}