package com.example.octalnews.domain.model.retrofit.weather

data class WeatherModel(
    val current_weather: CurrentWeather = CurrentWeather(),
    val elevation: Double = 0.0,
    val generationtime_ms: Double = 0.0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timezone: String = "",
    val timezone_abbreviation: String = "",
    val utc_offset_seconds: Int = 0
)