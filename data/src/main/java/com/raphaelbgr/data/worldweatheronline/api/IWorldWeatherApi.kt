package com.raphaelbgr.data.worldweatheronline.api

import retrofit2.http.GET
import com.raphaelbgr.data.worldweatheronline.model.LocalWeatherResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Query

interface IWorldWeatherApi {
    @GET("premium/v1/weather.ashx")
    fun getWeatherInfoByCity(
        @Query("key") key: String?,
        @Query("q") city: String?,
        @Query("format") format: String?,
        @Query("num_of_days") numOfDays: String?
    ): Deferred<LocalWeatherResponseDTO?>
}