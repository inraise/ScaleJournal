package com.example.octalnews.controller

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.ui.*
import com.example.octalnews.presentation.items.NewsCardScreen
import com.example.octalnews.presentation.screen.viewmodels.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SetupNavGraph(
    context: Activity,
    navController: NavHostController,
    auth: FirebaseAuth,
    newsViewModel: NewsViewModel,
    drNavController: NavController,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    ratesViewModel: RatesViewModel
) {
    NavHost(
        navController = navController,
        startDestination =
        if (auth.currentUser == null) Screens.LoginScreen.route
        else Screens.MainScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController, context, auth)
        }

        composable(Screens.AuthScreen.route) {
            AuthUserEmailScreen(navController, context, auth)
        }

        composable(Screens.MainScreen.route) {
            MainScreen(
                navController,
                newsViewModel,
                drNavController,
                auth,
                locationViewModel,
                context,
                weatherViewModel,
                ratesViewModel
            )
        }

        composable(Screens.NewsCard.route) {
            NewsCardScreen(navController)
        }

        composable(Screens.NewsList.route) {
            NewsListScreen(navController)
        }
    }
}