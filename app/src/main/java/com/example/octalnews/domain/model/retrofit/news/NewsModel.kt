package com.example.octalnews.domain.model.retrofit.news

data class NewsModel(
    val articles: List<Article> = listOf(),
    val status: String = "",
    val totalResults: Int = 0
)