package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RoutesItem(

	@field:SerializedName("sourceLat")
	val sourceLat: Double? = null,

	@field:SerializedName("fare")
	val fare: Double? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("destinationLong")
	val destinationLong: Double? = null,

	@field:SerializedName("sourceTime")
	val sourceTime: List<String?>? = null,

	@field:SerializedName("sourceTitle")
	val sourceTitle: String? = null,

	@field:SerializedName("sourceLong")
	val sourceLong: Double? = null,

	@field:SerializedName("destinationTime")
	val destinationTime: List<String?>? = null,

	@field:SerializedName("medium")
	val medium: String? = null,

	@field:SerializedName("rideEstimation")
	val rideEstimation: @RawValue Any? = null,

	@field:SerializedName("routeName")
	val routeName: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("destinationTitle")
	val destinationTitle: String? = null,

	@field:SerializedName("busRouteDetails")
	val busRouteDetails: BusRouteDetails? = null,

	@field:SerializedName("destinationLat")
	val destinationLat: Double? = null,

	@field:SerializedName("trails")
	val trailsItem: List<TrailsItem>?=null
):Parcelable