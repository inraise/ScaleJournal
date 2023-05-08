package com.example.octalnews.domain.model.retrofit.weather

data class CurrentWeather(
    val temperature: Double = 0.0,
    val time: String = "",
    val weathercode: Int = 0,
    val winddirection: Double = 0.0,
    val windspeed: Double = 0.0
)