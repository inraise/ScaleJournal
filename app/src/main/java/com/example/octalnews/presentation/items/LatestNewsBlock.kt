package com.example.octalnews.presentation.items

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.octalnews.R
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.domain.model.retrofit.news.Article
import com.example.octalnews.presentation.theme.fontInter
import com.example.octalnews.presentation.theme.fontStr
import com.example.octalnews.presentation.screen.viewmodels.LocationViewModel
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel

var list by mutableStateOf(listOf<Article>())
var text by mutableStateOf("")
var color by mutableStateOf(Color.Black)

@Composable
fun LatestNews(
    newsViewModel: NewsViewModel,
    navController: NavController,
    context: Activity,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel
) {
    locationViewModel.getUserLocation(context)
    weatherViewModel.getWeather(locationViewModel.lon, locationViewModel.lat)

    if (newsViewModel.allNews.articles.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF5EE))
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.padding(top = 40.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "SCALE JOURNAL",
                            color = Color(0xFF303030),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Medium,
                            fontFamily = fontStr,
                            fontSize = 28.sp
                        )

                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable { },
                            painter = painterResource(id = R.drawable.gr),
                            contentDescription = null
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .border(2.dp, Color(0xFF303030))
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))
                WeatherDataPiece(weatherViewModel)
            }

            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))

                Column {
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .border(2.dp, Color(0xFF6B3535))
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                list = newsViewModel.allNews.articles
                                text = "LATEST NEWS"
                                color = Color(0xFF6B3535)
                                navController.navigate(Screens.NewsList.route) {
                                    popUpTo(
                                        Screens.NewsList.route
                                    ) { inclusive = true }
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "LATEST NEWS",
                            color = Color(0xFF6B3535),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = fontInter,
                            fontSize = 24.sp
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "SEE MORE",
                                color = Color(0xFF6B3535),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = fontInter,
                                fontSize = 10.sp
                            )

                            Spacer(modifier = Modifier.size(4.dp))

                            Image(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.latestnewsarrow),
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    TitleNews(navController, newsViewModel.allNews.articles[0])

                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MoreNews(navController, newsViewModel.allNews.articles[1])
                        MoreNews(navController, newsViewModel.allNews.articles[2])
                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MoreNews(navController, newsViewModel.allNews.articles[3])
                        MoreNews(navController, newsViewModel.allNews.articles[4])
                    }
                }
            }

            if (newsViewModel.businessNews.articles.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.padding(top = 60.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color(0xFF0C4258))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    list = newsViewModel.businessNews.articles
                                    text = "BUSINESS NEWS"
                                    color = Color(0xFF0C4258)
                                    navController.navigate(Screens.NewsList.route) {
                                        popUpTo(
                                            Screens.NewsList.route
                                        ) { inclusive = true }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "BUSINESS NEWS",
                                color = Color(0xFF0C4258),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = fontInter,
                                fontSize = 24.sp
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "SEE MORE",
                                    color = Color(0xFF0C4258),
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = fontInter,
                                    fontSize = 10.sp
                                )

                                Spacer(modifier = Modifier.size(4.dp))

                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.business),
                                    contentDescription = null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        TitleNews(navController, newsViewModel.businessNews.articles[0])

                        Spacer(modifier = Modifier.padding(top = 30.dp))

                        if (newsViewModel.businessNews.articles.size >= 3) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.businessNews.articles[1])
                                MoreNews(navController, newsViewModel.businessNews.articles[2])
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        if (newsViewModel.businessNews.articles.size >= 5) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.businessNews.articles[3])
                                MoreNews(navController, newsViewModel.businessNews.articles[4])
                            }
                        }
                    }
                }
            }

            if (newsViewModel.scienceNews.articles.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.padding(top = 60.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color(0xFF226185))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    list = newsViewModel.scienceNews.articles
                                    text = "SCIENCE NEWS"
                                    color = Color(0xFF226185)
                                    navController.navigate(Screens.NewsList.route) {
                                        popUpTo(
                                            Screens.NewsList.route
                                        ) { inclusive = true }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "SCIENCE NEWS",
                                color = Color(0xFF226185),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = fontInter,
                                fontSize = 24.sp
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "SEE MORE",
                                    color = Color(0xFF226185),
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = fontInter,
                                    fontSize = 10.sp
                                )

                                Spacer(modifier = Modifier.size(4.dp))

                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.science),
                                    contentDescription = null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        TitleNews(navController, newsViewModel.scienceNews.articles[0])

                        Spacer(modifier = Modifier.padding(top = 30.dp))

                        if (newsViewModel.scienceNews.articles.size >= 3) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.scienceNews.articles[1])
                                MoreNews(navController, newsViewModel.scienceNews.articles[2])
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        if (newsViewModel.scienceNews.articles.size >= 5) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.scienceNews.articles[3])
                                MoreNews(navController, newsViewModel.scienceNews.articles[4])
                            }
                        }
                    }
                }
            }

            if (newsViewModel.technologyNews.articles.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.padding(top = 60.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color(0xFF5DC999))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    list = newsViewModel.technologyNews.articles
                                    text = "TECH. NEWS"
                                    color = Color(0xFF5DC999)
                                    navController.navigate(Screens.NewsList.route) {
                                        popUpTo(
                                            Screens.NewsList.route
                                        ) { inclusive = true }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "TECH. NEWS",
                                color = Color(0xFF5DC999),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = fontInter,
                                fontSize = 24.sp
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "SEE MORE",
                                    color = Color(0xFF5DC999),
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = fontInter,
                                    fontSize = 10.sp
                                )

                                Spacer(modifier = Modifier.size(4.dp))

                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.technology),
                                    contentDescription = null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        TitleNews(navController, newsViewModel.technologyNews.articles[0])

                        Spacer(modifier = Modifier.padding(top = 30.dp))

                        if (newsViewModel.technologyNews.articles.size >= 3) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.technologyNews.articles[1])
                                MoreNews(navController, newsViewModel.technologyNews.articles[2])
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        if (newsViewModel.technologyNews.articles.size >= 5) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.technologyNews.articles[3])
                                MoreNews(navController, newsViewModel.technologyNews.articles[4])
                            }
                        }
                    }
                }
            }

            if (newsViewModel.healthNews.articles.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.padding(top = 60.dp))

                    Column {
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color(0xFF8C2947))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    list = newsViewModel.healthNews.articles
                                    text = "HEALTH NEWS"
                                    color = Color(0xFF8C2947)
                                    navController.navigate(Screens.NewsList.route) {
                                        popUpTo(
                                            Screens.NewsList.route
                                        ) { inclusive = true }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "HEALTH NEWS",
                                color = Color(0xFF8C2947),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = fontInter,
                                fontSize = 24.sp
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "SEE MORE",
                                    color = Color(0xFF8C2947),
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = fontInter,
                                    fontSize = 10.sp
                                )

                                Spacer(modifier = Modifier.size(4.dp))

                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.health),
                                    contentDescription = null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        TitleNews(navController, newsViewModel.healthNews.articles[0])

                        Spacer(modifier = Modifier.padding(top = 30.dp))

                        if (newsViewModel.healthNews.articles.size >= 3) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.healthNews.articles[1])
                                MoreNews(navController, newsViewModel.healthNews.articles[2])
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        if (newsViewModel.healthNews.articles.size >= 5) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MoreNews(navController, newsViewModel.healthNews.articles[3])
                                MoreNews(navController, newsViewModel.healthNews.articles[4])
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF5EE)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF303030))
        }
    }
}
