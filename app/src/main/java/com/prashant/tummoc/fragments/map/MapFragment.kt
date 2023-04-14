package com.prashant.tummoc.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.prashant.tummoc.R
import com.prashant.tummoc.databinding.MapFragmentBinding

class MapFragment :Fragment(), OnMapReadyCallback {
    private lateinit var binding: MapFragmentBinding
    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = MapFragmentBinding.inflate(layoutInflater)
        try {
            MapsInitializer.initialize(requireActivity())
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            val latLng = LatLng(37.7749, -122.4194) // set the map's initial position
            val zoomLevel = 12f // set the map's initial zoom level
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}