package com.example.octalnews.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.octalnews.R
import com.example.octalnews.presentation.theme.fontInter
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel
import kotlin.math.roundToInt

@Composable
fun WeatherDataPiece(weatherViewModel: WeatherViewModel) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
            .background(
                Color(0xFFFEF0E6)
            )
            .padding(20.dp)
            .padding(start = 10.dp, end = 10.dp).clickable {
                uriHandler.openUri(
                    "https://openweathermap.org/weathermap?basemap=map&cities=false" +
                            "&layer=radar&lat=${weatherViewModel.currentWeather.latitude}" +
                            "&lon=${weatherViewModel.currentWeather.longitude}&zoom=14"
                )
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(70.dp),
            painter = painterResource(
                id = when (weatherViewModel.currentWeather.current_weather.weathercode) {
                    0 -> R.drawable.weathersun
                    61 -> R.drawable.weatherrain
                    63 -> R.drawable.weatherrain
                    65 -> R.drawable.weatherrain
                    66 -> R.drawable.weatherrain
                    67 -> R.drawable.weatherrain
                    71 -> R.drawable.weathersnow
                    73 -> R.drawable.weathersnow
                    75 -> R.drawable.weathersnow
                    77 -> R.drawable.weathersnow
                    80 -> R.drawable.weatherheavyrain
                    81 -> R.drawable.weatherheavyrain
                    82 -> R.drawable.weatherheavyrain
                    85 -> R.drawable.weatherheavysnow
                    86 -> R.drawable.weatherheavysnow
                    95 -> R.drawable.weatherthunder
                    96 -> R.drawable.weatherthunder
                    99 -> R.drawable.weatherthunder
                    else -> R.drawable.weathercloud
                }
            ),
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(6.dp))

                Text(
                    fontSize = 25.sp,
                    color = Color(0xFF303030),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = fontInter,
                    text = weatherViewModel.currentWeather.current_weather.temperature.roundToInt()
                        .toString() + "˙"
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(6.dp))

                Text(
                    fontSize = 13.sp,
                    color = Color(0xFF303030),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontInter,
                    text = weatherViewModel.currentWeather.current_weather.windspeed.toString() +
                            " m/s"
                )
            }
        }
    }
}