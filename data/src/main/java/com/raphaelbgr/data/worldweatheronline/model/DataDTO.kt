package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDTO(

    @field:SerializedName("request")
	val request: List<RequestDTO?>? = null,

    @field:SerializedName("current_condition")
	val currentCondition: List<CurrentConditionDTO?>? = null,

    @field:SerializedName("weather")
	val weather: List<WeatherDTO?>? = null,

    @field:SerializedName("ClimateAverages")
	val climateAverages: List<ClimateAveragesDTO?>? = null
) : Parcelable