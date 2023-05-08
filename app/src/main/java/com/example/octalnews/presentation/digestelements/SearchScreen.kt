package com.example.octalnews.presentation.digestelements

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.octalnews.R
import com.example.octalnews.presentation.items.TitleNews
import com.example.octalnews.presentation.theme.fontInter
import com.example.octalnews.presentation.theme.fontStr
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(newsViewModel: NewsViewModel, navController: NavController, context: Activity) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember {
        mutableStateOf("")
    }

    Spacer(modifier = Modifier.padding(top = 30.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(15.dp)),
            value = searchText,
            placeholder = {
                Text(
                    text = "Search News",
                    color = Color(0xFF303030),
                    fontFamily = fontInter,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            },
            onValueChange = { searchText = it },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFEF0E6),
                textColor = Color(0xFF303030),
                cursorColor = Color(0xFF303030),
                focusedIndicatorColor = Color(0xFFFEF0E6)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    newsViewModel.getSearchedNews(searchText, context)
                }
            )
        )
    }

    Spacer(modifier = Modifier.padding(top = 30.dp))

    if (newsViewModel.searchedNews.articles.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(newsViewModel.searchedNews.articles) { item ->
                TitleNews(navController = navController, newsData = item, clickToUrl = true)

                Spacer(modifier = Modifier.padding(top = 20.dp))
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.searching),
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(60.dp))

            Text(
                text = "HERE IS EMPTY!",
                color = Color(0xFF303030),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontFamily = fontStr,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                text = "You need to write\nyour text for search higher",
                color = Color(0xFF303030),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontInter,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
