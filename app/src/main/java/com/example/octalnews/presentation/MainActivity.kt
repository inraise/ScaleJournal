package com.example.octalnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.octalnews.controller.SetupNavGraph
import com.example.octalnews.data.ReadData
import com.example.octalnews.data.SaveData
import com.example.octalnews.presentation.screen.viewmodels.*
import com.example.octalnews.presentation.theme.OctalNewsTheme
import com.example.octalnews.presentation.screen.viewmodels.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val colorImageViewModel: ColorImageViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()
    private lateinit var navController: NavHostController
    private lateinit var auth: FirebaseAuth
    private val newsViewModel: NewsViewModel by viewModels()
    private val ratesViewModel: RatesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        if (ReadData("country", this) == "null") {
            SaveData("us", "country", this)
            SaveData("en", "lang", this)
        }

        setContent {
            OctalNewsTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setNavigationBarColor(
                    color = MaterialTheme.colors.background
                )
                colorImageViewModel.getIcon(MaterialTheme.colors.background)
                newsViewModel.getNews("", this)
                ratesViewModel.getRates()

                navController = rememberNavController()

                SetupNavGraph(
                    context = this,
                    colorImageViewModel = colorImageViewModel,
                    navController = navController,
                    auth,
                    newsViewModel,
                    navController,
                    locationViewModel,
                    weatherViewModel,
                    ratesViewModel
                )
            }
        }
    }
}
