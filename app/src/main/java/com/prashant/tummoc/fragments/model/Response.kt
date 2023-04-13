package com.prashant.tummoc.fragments.model

import com.google.gson.annotations.SerializedName
import com.prashant.tummoc.fragments.model.ResponseItem

data class Response(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)