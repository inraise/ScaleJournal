package com.example.octalnews.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.octalnews.R
import com.example.octalnews.presentation.items.digestelements.DigestDigestScreen
import com.example.octalnews.presentation.items.digestelements.ProfileScreen
import com.example.octalnews.presentation.items.digestelements.SearchScreen
import com.example.octalnews.presentation.screen.viewmodels.LocationViewModel
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.screen.viewmodels.RatesViewModel
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel
import com.example.octalnews.presentation.theme.fontInter
import com.example.octalnews.presentation.theme.fontStr
import com.google.firebase.auth.FirebaseAuth

var showElement by mutableStateOf("digest")

@Composable
fun DigestScreen(
    locationViewModel: LocationViewModel,
    auth: FirebaseAuth,
    navController: NavController,
    newsViewModel: NewsViewModel,
    weatherViewModel: WeatherViewModel,
    context: Activity,
    ratesViewModel: RatesViewModel
) {
    data class RowList(val title: String, val image: Int, val show: String)

    val activity = (LocalContext.current as? Activity)

    val rowList = listOf(
        RowList("Digest", R.drawable.book, "digest"),
        RowList("Search", R.drawable.searchicon, "search"),
        RowList("Profile", R.drawable.user, "profile")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5EE))
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    text = "SCALE DIGEST",
                    color = Color(0xFF303030),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fontStr,
                    fontSize = 28.sp
                )

                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            auth.signOut()
                            activity?.finish()
                        },
                    painter = painterResource(id = R.drawable.exitmenu),
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


        Spacer(modifier = Modifier.padding(top = 20.dp))

        LazyRow {
            items(rowList) { item ->
                Row(
                    modifier = Modifier
                        .padding(9.dp)
                        .width(120.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(10.dp))
                        .background(Color(0xFFFEF0E6))
                        .padding(3.dp)
                        .clickable { showElement = item.show },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = item.image),
                        contentDescription = null
                    )

                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = fontInter
                    )
                }
            }
        }

        when (showElement) {
            "digest" -> DigestDigestScreen(
                locationViewModel,
                context,
                weatherViewModel,
                ratesViewModel
            )
            "search" -> SearchScreen(newsViewModel, navController, context)
            "profile" -> ProfileScreen(auth, context, newsViewModel)
        }
    }
}