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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.domain.model.retrofit.Article
import com.example.octalnews.presentation.theme.fontInter

@Composable
fun MoreNews(navController: NavController, newsData: Article) {
    Column(
        modifier = Modifier
            .width(165.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
            .background(
                Color(0xFFFEF0E6)
            )
            .clickable {
                cardItem = newsData
                navController.navigate(Screens.NewsCard.route) {
                    popUpTo(Screens.NewsCard.route) {
                        inclusive = true
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFFEF0E6)),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(newsData.urlToImage),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            text = if (newsData.title.length <= 40) newsData.title else newsData.title.substring(
                0,
                40
            ) + "...",
            color = Color(0xFF303030),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Black,
            fontFamily = fontInter,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(top = 5.dp))

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (newsData.publishedAt != "") newsData.publishedAt.substring(
                    11,
                    newsData.publishedAt.length - 4
                ) else "",
                color = Color(0xFF303030),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontInter,
                fontSize = 10.sp,
                textAlign = TextAlign.Start
            )

            Text(
                text = if (newsData.source.name.length <= 18) newsData.source.name
                else newsData.source.name.substring(
                    0,
                    15
                ) + "...",
                color = Color(0xFF303030),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontInter,
                fontSize = 10.sp,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
    }
}