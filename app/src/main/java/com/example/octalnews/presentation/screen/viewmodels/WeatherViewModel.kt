package com.example.octalnews.presentation.screen.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import com.example.octalnews.domain.usecase.weather.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {
    var currentWeather by mutableStateOf(WeatherModel())

    fun getWeather(lon: Double, lat: Double) {
        viewModelScope.launch {
            try {
                currentWeather = weatherUseCase.invoke(lat, lon)
            } catch (_: java.lang.Exception) {
            }
        }
    }
}