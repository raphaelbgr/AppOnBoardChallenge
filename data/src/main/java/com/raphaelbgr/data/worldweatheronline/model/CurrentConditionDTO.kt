package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentConditionDTO(

    @field:SerializedName("observation_time")
	val observationTime: String? = null,

    @field:SerializedName("temp_C")
	val tempC: String? = null,

    @field:SerializedName("temp_F")
	val tempF: String? = null,

    @field:SerializedName("weatherCode")
	val weatherCode: String? = null,

    @field:SerializedName("weatherIconUrl")
	val weatherIconUrl: List<WeatherIconUrlDTO?>? = null,

    @field:SerializedName("weatherDesc")
	val weatherDesc: List<WeatherDescDTO?>? = null,

    @field:SerializedName("windspeedMiles")
	val windspeedMiles: String? = null,

    @field:SerializedName("windspeedKmph")
	val windspeedKmph: String? = null,

    @field:SerializedName("winddirDegree")
	val winddirDegree: String? = null,

    @field:SerializedName("winddir16Point")
	val winddir16Point: String? = null,

    @field:SerializedName("precipMM")
	val precipMM: String? = null,

    @field:SerializedName("precipInches")
	val precipInches: String? = null,

    @field:SerializedName("humidity")
	val humidity: String? = null,

    @field:SerializedName("visibility")
	val visibility: String? = null,

    @field:SerializedName("visibilityMiles")
	val visibilityMiles: String? = null,

    @field:SerializedName("pressure")
	val pressure: String? = null,

    @field:SerializedName("pressureInches")
	val pressureInches: String? = null,

    @field:SerializedName("cloudcover")
	val cloudcover: String? = null,

    @field:SerializedName("FeelsLikeC")
	val feelsLikeC: String? = null,

    @field:SerializedName("FeelsLikeF")
	val feelsLikeF: String? = null,

    @field:SerializedName("uvIndex")
	val uvIndex: String? = null
) : Parcelable