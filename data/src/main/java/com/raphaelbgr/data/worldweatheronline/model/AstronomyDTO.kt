package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstronomyDTO(

	@field:SerializedName("sunrise")
	val sunrise: String? = null,

	@field:SerializedName("sunset")
	val sunset: String? = null,

	@field:SerializedName("moonrise")
	val moonrise: String? = null,

	@field:SerializedName("moonset")
	val moonset: String? = null,

	@field:SerializedName("moon_phase")
	val moonPhase: String? = null,

	@field:SerializedName("moon_illumination")
	val moonIllumination: String? = null
) : Parcelable