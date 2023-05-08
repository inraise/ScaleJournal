package com.example.octalnews.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.octalnews.controller.BottomNavGraph
import com.example.octalnews.domain.model.BottomScreen
import com.example.octalnews.presentation.screen.viewmodels.LocationViewModel
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.screen.viewmodels.RatesViewModel
import com.example.octalnews.presentation.screen.viewmodels.WeatherViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    drNavController: NavController,
    auth: FirebaseAuth,
    locationViewModel: LocationViewModel,
    context: Activity,
    weatherViewModel: WeatherViewModel,
    ratesViewModel: RatesViewModel
) {
    val controller = rememberNavController()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color(0xFFFEF0E6),
            darkIcons = true
        )
    }

    Scaffold(
        modifier = Modifier.background(Color(0xFFFFF5EE)),
        bottomBar = { BottomBar(controller) }) {
        BottomNavGraph(
            controller,
            newsViewModel,
            drNavController,
            auth,
            locationViewModel,
            context,
            weatherViewModel,
            ratesViewModel
        )
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screen = listOf(
        BottomScreen.LatestNews,
        BottomScreen.TheDigest
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color(0xFFFEF0E6)) {
        screen.forEach { screen ->
            AddItem(screen = screen, current = currentDestination, controller = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(screen: BottomScreen, current: NavDestination?, controller: NavController) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = screen.icon),
                contentDescription = null
            )
        },
        selected = current?.hierarchy?.any { it.route == screen.route } == true,
        selectedContentColor = LocalContentColor.current.copy(
            red = 0.19f,
            green = 0.19f,
            blue = 0.19f
        ),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = { controller.navigate(screen.route) })
}