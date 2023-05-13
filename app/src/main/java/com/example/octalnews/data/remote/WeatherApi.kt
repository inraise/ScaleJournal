package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true
    ): WeatherModel
}