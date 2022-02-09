package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalWeatherResponseDTO(

	@field:SerializedName("data")
	val data: DataDTO? = null
) : Parcelable