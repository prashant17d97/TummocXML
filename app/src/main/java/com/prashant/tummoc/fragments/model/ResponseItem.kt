package com.prashant.tummoc.fragments.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.prashant.tummoc.genericadapters.AbstractModel
import com.prashant.tummoc.genericadapters.RecyclerAdapter
import com.prashant.tummoc.methods.CommonMethod.convertStringInTime
import com.prashant.tummoc.methods.CommonMethod.convertToTwoDecimalPlaces
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ResponseItem(

    @field:SerializedName("totalDuration")
    val totalDuration: String? = null,

    @field:SerializedName("routes")
    val routes: List<RoutesItem>? = null,

    @field:SerializedName("totalFare")
    val totalFare: Double? = null,

    @field:SerializedName("routeTitle")
    val routeTitle: String? = null,

    @field:SerializedName("totalDistance")
    val totalDistance: Double? = null,

    /**
     * 1-> Bus
     * 2-> Metro
     * */
    val routeType: Int = 1,

    var infoAdapter: @RawValue RecyclerAdapter<RoutesItem>
) : Parcelable, AbstractModel() {
    val estPrice: String
        get() = "₹ $totalFare"

    val travelTime: String
        get() = "~ ${totalDuration?.convertStringInTime() ?: ""}"

    val distance: String
        get() = "${convertToTwoDecimalPlaces(totalDistance ?: 0.0)} Kms"
}