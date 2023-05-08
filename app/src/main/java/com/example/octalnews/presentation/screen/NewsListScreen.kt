package com.example.octalnews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.octalnews.R
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.presentation.items.cardItem
import com.example.octalnews.presentation.items.color
import com.example.octalnews.presentation.items.list
import com.example.octalnews.presentation.items.text
import com.example.octalnews.presentation.theme.fontInter

@Composable
fun NewsListScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5EE))
            .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(
                modifier = Modifier.padding(top = 20.dp)
            )

            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.iconnew),
                contentDescription = null
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .border(2.dp, color)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                color = color,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontInter,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
        }

        items(list) { item ->
            Column(
                modifier = Modifier
                    .width(350.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
                    .background(
                        Color(0xFFFEF0E6)
                    )
                    .clickable {
                        cardItem = item
                        navController.navigate(Screens.NewsCard.route) {
                            popUpTo(Screens.NewsCard.route) {
                                inclusive = true
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
                    painter = rememberAsyncImagePainter(item.urlToImage),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    text = item.source.name,
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
                    text = item.title,
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
                    text = if (item.publishedAt != "") item.publishedAt.substring(
                        11,
                        item.publishedAt.length - 4
                    ) else "",
                    color = Color(0xFF303030),
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontInter,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
        }
    }
}