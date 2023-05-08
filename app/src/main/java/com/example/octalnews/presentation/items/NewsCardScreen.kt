package com.example.octalnews.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.octalnews.presentation.theme.fontInter

@Composable
fun NewsCardScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5EE))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFEF0E6)),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(cardItem.urlToImage),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .padding(top = 25.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.padding(top = 190.dp))

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFFFEF0E6),
                                    Color(0xFFFEF0E6)
                                )
                            ),
                            alpha = 0.9f
                        )
                        .padding(20.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = if (cardItem.publishedAt != "") cardItem.publishedAt.substring(
                            0,
                            10
                        ) + " " + cardItem.publishedAt.substring(
                            11,
                            cardItem.publishedAt.length - 4
                        ) else "",
                        color = Color(0xFF303030),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontInter,
                        fontSize = 14.sp
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .padding(top = 5.dp),
                        text = cardItem.title,
                        color = Color(0xFF303030),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Black,
                        fontFamily = fontInter,
                        fontSize = 18.sp
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .padding(top = 5.dp),
                        text = "Published by ${cardItem.source.name}",
                        color = Color(0xFF303030),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = fontInter,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = if (cardItem.description != null) cardItem.description else "",
                    color = Color(0xFF303030),
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fontInter,
                    fontSize = 17.sp
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(start = 15.dp, end = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color(0xFF303030),
                                        Color(0xFF303030)
                                    )
                                ),
                                alpha = 0.4f
                            )
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    Text(
                        text =
                        if (cardItem.author != null)
                            cardItem.author
                        else
                            cardItem.source.name,
                        color = Color(0xFF303030),
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontInter,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Button(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF303030)
                    ),
                    onClick = {
                        uriHandler.openUri(cardItem.url)
                    }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Read Now",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontInter,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
