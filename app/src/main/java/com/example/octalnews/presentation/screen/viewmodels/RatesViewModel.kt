package com.example.octalnews.presentation.screen.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import com.example.octalnews.data.remote.RatesRetrofitInterface
import kotlinx.coroutines.launch

class RatesViewModel : ViewModel() {
    var ratesData by mutableStateOf(RatesModel())

    fun getRates() {
        viewModelScope.launch {
            try {
                ratesData = RatesRetrofitInterface.rates_api_service.getRates()
            } catch (_: java.lang.Exception) {
            }
        }
    }
}