package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.prashant.tummoc.fragments.model.RoutesItem
import com.prashant.tummoc.genericadapters.AbstractModel
import kotlinx.parcelize.Parcelize
@Parcelize
data class ResponseItem(

	@field:SerializedName("totalDuration")
	val totalDuration: String? = null,

	@field:SerializedName("routes")
	val routes: List<RoutesItem?>? = null,

	@field:SerializedName("totalFare")
	val totalFare: Double? = null,

	@field:SerializedName("routeTitle")
	val routeTitle: String? = null,

	@field:SerializedName("totalDistance")
	val totalDistance: Double? = null
):Parcelable,AbstractModel()