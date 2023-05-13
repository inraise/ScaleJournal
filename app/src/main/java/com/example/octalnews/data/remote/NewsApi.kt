package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("top-headlines")
    suspend fun getHeadlinesNews(
        @Query("country") country: String,
        @Query("category") category: String = "",
        @Query("pageSize") pageSize: String = "50",
        @Query("apiKey") apiKey: String = "cbcff4cc818843b6a289d332ecd1109d"
    ): NewsModel


    @GET("everything")
    suspend fun getSearchedNews(
        @Query("language") country: String,
        @Query("pageSize") pageSize: String = "100",
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = "cbcff4cc818843b6a289d332ecd1109d"
    ): NewsModel
}