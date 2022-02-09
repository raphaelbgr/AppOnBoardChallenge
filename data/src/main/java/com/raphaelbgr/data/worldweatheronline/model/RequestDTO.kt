package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestDTO(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("query")
	val query: String? = null
) : Parcelable