package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TrailsItem(

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("time")
	val time: @RawValue Any? = null,

	@field:SerializedName("fareStage")
	val fareStage: String? = null,

	@field:SerializedName("seq")
	val seq: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
):Parcelable