package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherIconUrlDTO(

	@field:SerializedName("value")
	val value: String? = null
) : Parcelable