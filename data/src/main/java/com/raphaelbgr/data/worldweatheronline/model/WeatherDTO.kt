package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO(

    @field:SerializedName("date")
	val date: String? = null,

    @field:SerializedName("astronomy")
	val astronomy: List<AstronomyDTO?>? = null,

    @field:SerializedName("maxtempC")
	val maxtempC: String? = null,

    @field:SerializedName("maxtempF")
	val maxtempF: String? = null,

    @field:SerializedName("mintempC")
	val mintempC: String? = null,

    @field:SerializedName("mintempF")
	val mintempF: String? = null,

    @field:SerializedName("avgtempC")
	val avgtempC: String? = null,

    @field:SerializedName("avgtempF")
	val avgtempF: String? = null,

    @field:SerializedName("totalSnow_cm")
	val totalSnowCm: String? = null,

    @field:SerializedName("sunHour")
	val sunHour: String? = null,

    @field:SerializedName("uvIndex")
	val uvIndex: String? = null,

    @field:SerializedName("hourly")
	val hourly: List<HourlyDTO?>? = null
) : Parcelable