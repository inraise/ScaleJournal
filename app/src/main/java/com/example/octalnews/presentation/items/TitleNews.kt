package com.example.octalnews.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.domain.model.retrofit.news.Article
import com.example.octalnews.presentation.theme.fontInter

var cardItem by mutableStateOf(Article())

@Composable
fun TitleNews(navController: NavController, newsData: Article, clickToUrl: Boolean = false) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
            .background(
                Color(0xFFFEF0E6)
            )
            .clickable {
                cardItem = newsData

                if (clickToUrl) {
                    uriHandler.openUri(cardItem.url)
                } else {
                    navController.navigate(Screens.NewsCard.route) {
                        popUpTo(Screens.NewsCard.route) {
                            inclusive = true
                        }
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFFEF0E6)),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(newsData.urlToImage),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))

        Text(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            text = newsData.source.name,
            color = Color(0xFF303030),
            fontWeight = FontWeight.ExtraBold,
            fontFamily = fontInter,
            fontSize = 13.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            text = newsData.title,
            color = Color(0xFF303030),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Black,
            fontFamily = fontInter,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            text = if (newsData.publishedAt != "") newsData.publishedAt.substring(
                11,
                newsData.publishedAt.length - 4
            ) else "",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 12.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
    }
}