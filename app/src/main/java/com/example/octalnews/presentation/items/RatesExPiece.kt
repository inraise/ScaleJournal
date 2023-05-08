package com.example.octalnews.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.octalnews.presentation.theme.fontInter
import kotlin.math.roundToInt

@Composable
fun RatesExPiece(number: Double, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp, start = 30.dp, end = 30.dp, bottom = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            fontSize = 19.sp,
            color = Color(0xFF303030),
            fontWeight = FontWeight.ExtraBold,
            fontFamily = fontInter,
            text = text
        )

        Text(
            fontSize = 18.sp,
            color = Color(0xFF302C2C),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            text = ((1 / number * 100).roundToInt() / 100.0).toString() + " ₽"
        )
    }
}