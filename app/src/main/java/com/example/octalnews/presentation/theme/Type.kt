package com.example.octalnews.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.octalnews.R

val fontInter = FontFamily(
    Font(R.font.interbold, FontWeight.Bold),
    Font(R.font.interexbold, FontWeight.ExtraBold),
    Font(R.font.intersembold, FontWeight.SemiBold),
    Font(R.font.interreg, FontWeight.Medium),
    Font(R.font.interlight, FontWeight.Light),
    Font(R.font.interblack, FontWeight.Black)
)

val fontStr = FontFamily(
    Font(R.font.thickthinks, FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)