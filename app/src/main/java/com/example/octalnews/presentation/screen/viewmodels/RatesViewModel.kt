package com.example.octalnews.presentation.screen.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import com.example.octalnews.domain.usecase.rates.RatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(val ratesUseCase: RatesUseCase) : ViewModel() {
    var ratesData by mutableStateOf(RatesModel())

    fun getRates() {
        viewModelScope.launch {
            try {
                ratesData = ratesUseCase.invoke()
            } catch (_: java.lang.Exception) {
            }
        }
    }
}