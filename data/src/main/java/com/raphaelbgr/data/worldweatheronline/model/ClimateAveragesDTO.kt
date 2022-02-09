package com.raphaelbgr.data.worldweatheronline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClimateAveragesDTO(

	@field:SerializedName("month")
	val month: List<MonthDTO?>? = null
) : Parcelable