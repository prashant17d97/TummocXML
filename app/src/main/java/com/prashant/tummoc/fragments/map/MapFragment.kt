package com.prashant.tummoc.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.prashant.tummoc.R
import com.prashant.tummoc.databinding.MapFragmentBinding
import com.prashant.tummoc.fragments.model.RoutesItem
import com.prashant.tummoc.fragments.model.TrailsItem
import com.prashant.tummoc.methods.CommonMethod.bitmapDescriptorResource

/**
 *This is a MapFragment class that extends Fragment class in Kotlin.
 * It represents a map view with a polyline on it, showing the route of the journey from source
 * to destination.
 */
class MapFragment :Fragment() {
    // Bindings and variables initialization
    private lateinit var binding: MapFragmentBinding
    private val args by navArgs<MapFragmentArgs>()
    private val mapVM by viewModels<MapVM>()
    private lateinit var source: LatLng
    private lateinit var destination: LatLng
    private lateinit var routes: List<RoutesItem>

    /**
     * This function is used to create the view associated with this fragment and returns the root view for this fragment.
     * @param inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
     * @return View
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = MapFragmentBinding.inflate(layoutInflater)
        binding.viewModel = mapVM
        readArgs()
        initializeMap()
        return binding.root
    }

    /**
     * This function reads the arguments passed to the fragment and initializes source, destination, and routes variables.
     */
    private fun readArgs() {
        args.routes.routes?.let {
            routes = it
            mapVM.routesItem = it
            mapVM.updateUI()
        }
        source = LatLng(
            routes[0].sourceLat ?: 0.0, routes[0].sourceLong ?: 0.0
        )

        destination = LatLng(
            routes[routes.size - 1].destinationLat ?: 0.0,
            routes[routes.size - 1].destinationLong ?: 0.0
        )

    }

    /**
     * This function initializes the Google Map fragment and adds the polyline route to it.
     */
    private fun initializeMap() {
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        // Async map
        supportMapFragment.getMapAsync { googleMap ->
            mapVM.googleMap = googleMap
            addPolyLine()
        }
    }

    /**
     *This function adds a polyline on the map for each route in the list of routes.
     *It loops through each route and checks if the medium of the route is "Bus" or "Metro".
     *If the medium is "Bus" or "Metro", it uses the latLng() function to get the list of
     * latitudes and longitudes
     *of each trail in the route. Otherwise, it creates a list of latitudes and longitudes
     * consisting of the source and
     *destination of the route.
     *If the current index is the first or last route, it adds a marker on the map at the position
     * of either the source
     *or destination of the route, using the ic_location icon.
     *It creates a polyline with the latLngList, sets the color to R.color._FF18B9E3, sets the
     *width to 10f,
     *sets the pattern to a dash if the list has more than 2 items, and sets the zIndex to 1f.
     *Finally, it adds the polyline to the map and animates the camera to the source location with
     * zoom level 16.
     */
    private fun addPolyLine() {
        routes.forEachIndexed { index, routesItem ->
            val latLngList = routesItem.trailsItem?.let { latLng(it) }.takeIf {
                routesItem.medium.equals("Bus", true) || routesItem.medium.equals(
                    "Metro", true
                )
            } ?: listOf(
                LatLng(routesItem.sourceLat ?: 0.0, routesItem.sourceLong ?: 0.0), LatLng(
                    routesItem.destinationLat ?: 0.0, routesItem.destinationLong ?: 0.0
                )
            )
            if (index == 0 || index == routes.size - 1) {
                val markerOptions = MarkerOptions()

                markerOptions.position(source.takeIf { index == 0 }
                    ?: latLngList[latLngList.lastIndex])
                markerOptions.icon(
                    bitmapDescriptorResource(
                        requireContext(), R.drawable.ic_location
                    )
                )
                mapVM.googleMap.addMarker(markerOptions)
            }
            val polylineOptions = PolylineOptions()
            polylineOptions.addAll(latLngList)
            polylineOptions.color(requireContext().getColor(R.color._FF18B9E3))
            polylineOptions.width(10f)
            polylineOptions.pattern(
                listOf(
                    Dash(1f), Gap(0f)
                ).takeIf { latLngList.size > 2 } ?: listOf(
                    Dot(), Gap(3f)
                ),
            )
            polylineOptions.zIndex(1f)
            mapVM.googleMap.addPolyline(polylineOptions)
            mapVM.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source, 16f))
        }
    }

    /** This private function takes a
     * @param list of TrailsItem objects. It iterates over the list of TrailsItem objects and adds a new LatLng object to the
     *list for each TrailsItem object, using the latitude and longitude values from the
     *TrailsItem object.
     *If a latitude or longitude value is null, it uses a default value of 0.0.
     *The function the list of LatLng objects.
     * @return list of LatLng objects.
     */
    private fun latLng(list: List<TrailsItem>): List<LatLng> {
        val latLng = arrayListOf<LatLng>()
        list.forEach {
            latLng.add(LatLng(it.latitude ?: 0.0, it.longitude ?: 0.0))
        }
        return latLng
    }
}