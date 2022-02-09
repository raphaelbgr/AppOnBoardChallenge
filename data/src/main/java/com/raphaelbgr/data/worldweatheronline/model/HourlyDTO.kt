package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyDTO(

    @field:SerializedName("time")
	val time: String? = null,

    @field:SerializedName("tempC")
	val tempC: String? = null,

    @field:SerializedName("tempF")
	val tempF: String? = null,

    @field:SerializedName("windspeedMiles")
	val windspeedMiles: String? = null,

    @field:SerializedName("windspeedKmph")
	val windspeedKmph: String? = null,

    @field:SerializedName("winddirDegree")
	val winddirDegree: String? = null,

    @field:SerializedName("winddir16Point")
	val winddir16Point: String? = null,

    @field:SerializedName("weatherCode")
	val weatherCode: String? = null,

    @field:SerializedName("weatherIconUrl")
	val weatherIconUrl: List<WeatherIconUrlDTO?>? = null,

    @field:SerializedName("weatherDesc")
	val weatherDesc: List<WeatherDescDTO?>? = null,

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

    @field:SerializedName("HeatIndexC")
	val heatIndexC: String? = null,

    @field:SerializedName("HeatIndexF")
	val heatIndexF: String? = null,

    @field:SerializedName("DewPointC")
	val dewPointC: String? = null,

    @field:SerializedName("DewPointF")
	val dewPointF: String? = null,

    @field:SerializedName("WindChillC")
	val windChillC: String? = null,

    @field:SerializedName("WindChillF")
	val windChillF: String? = null,

    @field:SerializedName("WindGustMiles")
	val windGustMiles: String? = null,

    @field:SerializedName("WindGustKmph")
	val windGustKmph: String? = null,

    @field:SerializedName("FeelsLikeC")
	val feelsLikeC: String? = null,

    @field:SerializedName("FeelsLikeF")
	val feelsLikeF: String? = null,

    @field:SerializedName("chanceofrain")
	val chanceofrain: String? = null,

    @field:SerializedName("chanceofremdry")
	val chanceofremdry: String? = null,

    @field:SerializedName("chanceofwindy")
	val chanceofwindy: String? = null,

    @field:SerializedName("chanceofovercast")
	val chanceofovercast: String? = null,

    @field:SerializedName("chanceofsunshine")
	val chanceofsunshine: String? = null,

    @field:SerializedName("chanceoffrost")
	val chanceoffrost: String? = null,

    @field:SerializedName("chanceofhightemp")
	val chanceofhightemp: String? = null,

    @field:SerializedName("chanceoffog")
	val chanceoffog: String? = null,

    @field:SerializedName("chanceofsnow")
	val chanceofsnow: String? = null,

    @field:SerializedName("chanceofthunder")
	val chanceofthunder: String? = null,

    @field:SerializedName("uvIndex")
	val uvIndex: String? = null
) : Parcelable