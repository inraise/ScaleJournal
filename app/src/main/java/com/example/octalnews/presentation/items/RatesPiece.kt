package com.example.octalnews.presentation.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.octalnews.presentation.screen.viewmodels.RatesViewModel

@Composable
fun RatesPiece(ratesViewModel: RatesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
            .background(
                Color(0xFFFEF0E6)
            )
            .padding(15.dp)
            .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        RatesExPiece(ratesViewModel.ratesData.rates.USD, "USD")
        RatesExPiece(ratesViewModel.ratesData.rates.EUR, "EUR")
        RatesExPiece(ratesViewModel.ratesData.rates.GBP, "GBP")
        RatesExPiece(ratesViewModel.ratesData.rates.CNY, "CNY")
    }
}