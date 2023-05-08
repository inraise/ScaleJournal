package com.example.octalnews.domain.model

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login")
    object AuthScreen : Screens("auth")
    object NewsCard : Screens("newscard")
    object MainScreen : Screens("main")
    object NewsList : Screens("list")
}