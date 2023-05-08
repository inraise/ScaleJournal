package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRetrofitInterface {
    companion object {
        const val BASE_URL = "https://api.open-meteo.com/"

        val weather_api_service: WeatherRetrofitInterface by lazy {
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherRetrofitInterface::class.java)
        }
    }

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true
    ): WeatherModel
}