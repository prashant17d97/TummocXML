package com.prashant.tummoc.fragments.home

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.prashant.tummoc.R
import com.prashant.tummoc.activity.MainActivity.Companion.weakReference
import com.prashant.tummoc.fragments.home.model.Routes
import com.prashant.tummoc.fragments.model.JsonFile.Companion.jsonFile
import com.prashant.tummoc.fragments.model.ResponseItem
import com.prashant.tummoc.fragments.model.RoutesItem
import com.prashant.tummoc.genericadapters.RecyclerAdapter

/**

 *This is a ViewModel class for HomeFragment, which handles the business logic and data for the
 * HomeFragment.
 *It contains ObservableFields for source, destination and likeSource, likeDestination to keep
 * track of the user's inputs.
 *It also contains viewPagerAdapter which holds the routes of the metro and bus, infoAdapter which
holds the details of each route,
 *and metroRoutes, busRoutes which hold the list of routes for each transport mode.
 *@constructor Initializes the busRoutes, metroRoutes, infoAdapter, and viewPagerAdapter by adding
 *  items from the JSON file.
 *Sets the onItemClickListener for metroRoutes and busRoutes, and navigates to the MapFragment on
 * item click.
 *@param None
 *@return None
 */
class HomeVM :ViewModel() {

    val likeSource =
        ObservableField(false) // ObservableField to keep track of the user's like on source.

    val likeDestination =
        ObservableField(false) // ObservableField to keep track of the user's like on destination.

    val source = ObservableField("") // ObservableField to hold the user's source input.

    val destination = ObservableField("") // ObservableField to hold the user's destination input.

    val viewPagerAdapter =
        RecyclerAdapter<Routes>(R.layout.routes) // Adapter to hold the view for each transport mode.

    private val metroRoutes =
        RecyclerAdapter<ResponseItem>(R.layout.routes_card) // Adapter to hold the list of metro routes.

    private val busRoutes =
        RecyclerAdapter<ResponseItem>(R.layout.routes_card) // Adapter to hold the list of bus routes.

    private val infoAdapter =
        RecyclerAdapter<RoutesItem>(R.layout.info_card) // Adapter to hold the details of each route.


    init {
        busRoutes.addItems(jsonFile.routeList) // Add items to the busRoutes from the JSON file.

        busRoutes.getAllItems().forEachIndexed { _, responseItem ->
            responseItem.routes?.let { infoAdapter.addItems(it) } // Add items to the infoAdapter from the responseItem.

            responseItem.infoAdapter = infoAdapter // Set the infoAdapter for the responseItem.
        }
        viewPagerAdapter.addItems(
            listOf(
                Routes(metroRoutes),
                Routes(busRoutes),
            )
        ) // Add items to the viewPagerAdapter.
        metroRoutes.setOnItemClick { view, _, position ->
            val routes = busRoutes.getItemAt(position)
            when (view.id) {
                R.id.clRoute -> {
                    view.findNavController() // Get the NavController from the View.
                        .navigate(HomeFragmentDirections.actionHomeFragmentToMapFragment(routes)) // Navigate to MapFragment with routes as the argument.
                }
            }
        } // Set the onItemClickListener for metroRoutes.
        busRoutes.setOnItemClick { view, _, position ->
            val routes = busRoutes.getItemAt(position)
            when (view.id) {
                R.id.clRoute -> {
                    view.findNavController() // Get the NavController from the View.
                        .navigate(HomeFragmentDirections.actionHomeFragmentToMapFragment(routes)) // Navigate to MapFragment with routes as the argument.
                }
            }
        } // Set the onItemClickListener for busRoutes.
    }

    /**

     *This function is called when a view is clicked.
     *It updates the corresponding ObservableField based on the clicked view.
     *@param view The view that was clicked.
     *@return None
     */
    fun onClick(view: View) {
        when (view.id) {
            R.id.ivLikeDestination -> likeDestination.set(!(likeDestination.get() ?: false))
            R.id.ivLikeSource -> likeSource.set(!(likeSource.get() ?: false))
            R.id.ivBack -> weakReference.get()
                ?.finishAffinity() // Finish the activity and all activities
        }
    }
}
