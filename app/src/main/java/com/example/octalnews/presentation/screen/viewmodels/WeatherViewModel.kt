package com.example.octalnews.presentation.screen.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import com.example.octalnews.data.remote.WeatherRetrofitInterface
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var currentWeather by mutableStateOf(WeatherModel())

    fun getWeather(lon: Double, lat: Double) {
        viewModelScope.launch {
            try {
                currentWeather = WeatherRetrofitInterface.weather_api_service.getWeather(lat, lon)
            } catch (_: java.lang.Exception) {
            }
        }
    }
}