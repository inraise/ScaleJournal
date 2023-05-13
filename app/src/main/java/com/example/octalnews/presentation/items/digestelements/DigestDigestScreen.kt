package com.example.octalnews.presentation.items.digestelements

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.octalnews.presentation.items.RatesPiece
import com.example.octalnews.presentation.items.WeatherDataPiece
import com.example.octalnews.presentation.screen.viewmodels.LocationViewModel
import com.example.octalnews.presentation.screen.viewmodels.RatesViewModel
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel

@Composable
fun DigestDigestScreen(
    locationViewModel: LocationViewModel,
    context: Activity,
    weatherViewModel: WeatherViewModel,
    ratesViewModel: RatesViewModel
) {
    locationViewModel.getUserLocation(context)
    weatherViewModel.getWeather(locationViewModel.lon, locationViewModel.lat)

    Spacer(modifier = Modifier.padding(top = 30.dp))
    WeatherDataPiece(weatherViewModel)

    Spacer(modifier = Modifier.padding(top = 20.dp))
    RatesPiece(ratesViewModel)
}