package com.prashant.tummoc.fragments.map

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.prashant.tummoc.R
import com.prashant.tummoc.activity.MainActivity
import com.prashant.tummoc.activity.MainActivity.Companion.weakReference
import com.prashant.tummoc.fragments.model.RoutesItem
import com.prashant.tummoc.fragments.model.TrailsItem
import com.prashant.tummoc.genericadapters.RecyclerAdapter
import com.prashant.tummoc.methods.CommonMethod.bitmapDescriptorResource
import com.prashant.tummoc.methods.CommonMethod.convertStringInTime
import com.prashant.tummoc.methods.CommonMethod.convertToTwoDecimalPlaces

/**
 *[ViewModel] class for MapFragment.
 */
class MapVM :ViewModel() {

    /**
     * [lateinit] properties
     * */
    // List of all routes for the trip.
    lateinit var routesItem: List<RoutesItem>

    // Destination of the trip.
    lateinit var transPort: List<RoutesItem>

    // GoogleMap object.
    lateinit var googleMap: GoogleMap

    /**
     * [lazy] properties
     * */
    // Source of the trip.
    val source: RoutesItem by lazy {
        routesItem.first()
    }
    val destination: RoutesItem by lazy {
        routesItem.last()
    }

    // Observable field to store the source transport type.
    val transSource = ObservableField("")

    // Observable field to store the destination transport type.
    val tranDestination = ObservableField("")


    // Observable field to store the duration of the trip.
    val duration = ObservableField("")

    // Observable field to store the amount of the trip.
    val amount = ObservableField("")

    // Observable field to store the distance of the trip.
    val distance = ObservableField("")

    // Observable field to store the name of the first trail.
    val firstTrail = ObservableField("")

    // Observable field to store the distance of the first trail.
    val trailDistance = ObservableField("")

    // RecyclerAdapter to show the trails.
    val trailAdapter = RecyclerAdapter<TrailsItem>(R.layout.trails)

    // Observable field to check if the trails are visible or not.
    val isTraitsVisible = ObservableField(false)

    // Previous marker of the trail.
    private lateinit var previousMarker: Marker

    // Fare of the trip.
    private var fare: Double = 0.0


    /**
     * Handles onClick events for views in the associated layout.
     * @param view the view that was clicked
     */
    fun onClick(view: View) {
        when (view.id) {
            R.id.ivBack -> view.findNavController().popBackStack()
            R.id.tvTrails -> {
                isTraitsVisible.set(!(isTraitsVisible.get() ?: false))
            }
        }
    }

    /**
     * Updates the UI with the latest data.
     */
    fun updateUI() {
        //Set the duration and distance fields based on the first item in the `routesItem` list
        duration.set("${routesItem[0].duration?.convertStringInTime()}")
        distance.set("${convertToTwoDecimalPlaces(routesItem[0].distance ?: 0.0)} KM")

        // Set the source and destination fields based on the first and last items in the `routesItem` list respectively
        transPort = getMiddleItems(routesItem)
        transSource.set(transPort.first().sourceTitle ?: "")
        tranDestination.set(transPort.last().destinationTitle ?: "")

        // Iterate through each item in the `transPort` list, adding any associated `trailsItem` to the `trailAdapter` list and updating the `fare` variable
        transPort.forEach {
            it.trailsItem?.let { it1 -> trailAdapter.addItems(it1) }
            fare += it.fare ?: 0.0
        }

        // Set the first trail and trail distance fields based on the first item in the `trailAdapter` list
        if (trailAdapter.getAllItems().isNotEmpty()) {
            val firstTrailsItem = trailAdapter.getItemAt(0)
            firstTrail.set(firstTrailsItem.trailName)
            trailDistance.set(firstTrailsItem.getDistance)
        }

        // Set the amount field based on the `duration` and `fare` variables
        amount.set(
            "~ ${source.duration?.convertStringInTime()}, ₹ ${
                convertToTwoDecimalPlaces(
                    fare
                )
            }"
        )

        // Remove the first item from the `trailAdapter` list and set the `onItemClick` listener for each item in the list
        trailAdapter.getAllItems().remove(trailAdapter.getAllItems().first())
        trailAdapter.setOnItemClick { view, _, position ->
            when (view.id) {
                R.id.clTrail -> {
                    // When a trail item is clicked, add a marker at the corresponding location on the map
                    val currentPosition = trailAdapter.getItemAt(position)
                    addMarkerOnClick(
                        LatLng(
                            currentPosition.latitude ?: 0.0,
                            currentPosition.longitude ?: 0.0
                        )
                    )
                }
            }
        }

    }

    /**
     * Gets the middle items from a list.
     * @param list the list to extract items from
     * @return a list containing the middle items of the original list
     */
    private fun <Generic> getMiddleItems(list: List<Generic>): List<Generic> {
        return if (list.size <= 2) {
            emptyList()
        } else {
            list.subList(1, list.size - 1)
        }
    }

    /**
     * Adds a marker to the map at the specified location, removing the previous marker if it exists.
     * @param latLng the location to add the marker on the map.
     */
    private fun addMarkerOnClick(latLng: LatLng) {
        if (::previousMarker.isInitialized) {
            previousMarker.remove()
        }
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.icon(
            bitmapDescriptorResource(
                (weakReference.get() as MainActivity), R.drawable.ic_navigation
            )
        )
        val marker = googleMap.addMarker(markerOptions)
        if (marker != null) {
            previousMarker = marker
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }
}
