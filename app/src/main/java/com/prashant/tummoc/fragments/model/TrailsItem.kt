package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.prashant.tummoc.genericadapters.AbstractModel
import com.prashant.tummoc.methods.CommonMethod.convertToTwoDecimalPlaces
import com.prashant.tummoc.methods.CommonMethod.getFirstTwoWords
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
) :Parcelable, AbstractModel() {
    val trailName: String
        get() = name?.getFirstTwoWords() ?: ""

    val getDistance: String
        get() = "${convertToTwoDecimalPlaces(distance ?: 0.0)} KM"
}