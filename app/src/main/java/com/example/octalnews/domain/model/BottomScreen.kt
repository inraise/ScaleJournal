package com.example.octalnews.domain.model

import com.example.octalnews.R

sealed class BottomScreen(val route: String, val title: String, val icon: Int) {
    object LatestNews : BottomScreen(
        "latestnews",
        "Latest News",
        R.drawable.alert
    )

    object TheDigest : BottomScreen(
        "digest",
        "The Digest",
        R.drawable.digestnews
    )
}