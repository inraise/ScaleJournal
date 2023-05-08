package com.example.octalnews.controller

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.octalnews.domain.model.BottomScreen
import com.example.octalnews.ui.DigestScreen
import com.example.octalnews.presentation.items.LatestNews
import com.example.octalnews.presentation.screen.viewmodels.LocationViewModel
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.screen.viewmodels.RatesViewModel
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    drNavController: NavController, auth: FirebaseAuth,
    locationViewModel: LocationViewModel, context: Activity, weatherViewModel: WeatherViewModel,
    ratesViewModel: RatesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.LatestNews.route
    ) {
        composable(BottomScreen.LatestNews.route) {
            LatestNews(newsViewModel, drNavController, context, locationViewModel, weatherViewModel)
        }

        composable(BottomScreen.TheDigest.route) {
            DigestScreen(
                locationViewModel,
                auth,
                navController,
                newsViewModel,
                weatherViewModel,
                context,
                ratesViewModel
            )
        }
    }
}