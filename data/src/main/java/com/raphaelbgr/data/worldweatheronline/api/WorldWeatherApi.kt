package com.raphaelbgr.data.worldweatheronline.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.raphaelbgr.data.worldweatheronline.viewmodel.WorldWeatherOnlineLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class WorldWeatherApi {

    private val BASE_URL = "https://api.worldweatheronline.com/"
    private val API_KEY = "9fe4559c1cba44c1b67235349220202"
    private val FORMAT = "json"
    private val NUM_OF_DAYS = "5"
    private var apiInstance: IWorldWeatherApi

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val gson = GsonBuilder()
        .create()

    private val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))

    init {
        apiInstance = builder.baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(IWorldWeatherApi::class.java)
    }

    fun getWeatherInfoByCity(city: String, callback: WorldWeatherOnlineLiveData.Callback) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                try {
                    val result =
                        apiInstance.getWeatherInfoByCity(API_KEY, city, FORMAT, NUM_OF_DAYS)
                            .await()
                    if (result != null) {
                        callback.onWeatherInfoSuccess(result)
                    } else {
                        callback.onWeatherInfoFailure(city)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    callback.onWeatherInfoFailure(city)
                }
            }
        }
    }
}