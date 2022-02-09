package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MonthDTO(

	@field:SerializedName("index")
	val index: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avgMinTemp")
	val avgMinTemp: String? = null,

	@field:SerializedName("avgMinTemp_F")
	val avgMinTempF: String? = null,

	@field:SerializedName("absMaxTemp")
	val absMaxTemp: String? = null,

	@field:SerializedName("absMaxTemp_F")
	val absMaxTempF: String? = null,

	@field:SerializedName("avgDailyRainfall")
	val avgDailyRainfall: String? = null
) : Parcelable