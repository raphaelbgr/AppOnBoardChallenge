package com.raphaelbgr.data.worldweatheronline.viewmodel

import androidx.lifecycle.LiveData
import com.raphaelbgr.data.worldweatheronline.api.WorldWeatherApi
import com.raphaelbgr.data.worldweatheronline.model.LocalWeatherResponseDTO

class WorldWeatherOnlineLiveData : LiveData<LocalWeatherResponseDTO?>() {
    private val api = WorldWeatherApi()

    fun getWeatherData(city: String) {
        return api.getWeatherInfoByCity(city, callback)
    }

    private val callback = object : Callback {

        override fun onWeatherInfoSuccess(success: LocalWeatherResponseDTO) {
            postValue(success)
        }

        override fun onWeatherInfoFailure(city: String) {
            postValue(null)
        }
    }

    interface Callback {
        fun onWeatherInfoSuccess(success: LocalWeatherResponseDTO)
        fun onWeatherInfoFailure(city: String)
    }
}