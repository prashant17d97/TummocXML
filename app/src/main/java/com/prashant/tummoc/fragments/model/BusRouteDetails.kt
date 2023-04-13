package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BusRouteDetails(

	@field:SerializedName("routeId")
	val routeId: @RawValue Any? = null,

	@field:SerializedName("routeNumber")
	val routeNumber: String? = null,

	@field:SerializedName("routeDescription")
	val routeDescription: String? = null,

	@field:SerializedName("routeName")
	val routeName: String? = null
):Parcelable