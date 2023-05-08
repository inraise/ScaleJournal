package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.NewsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "cbcff4cc818843b6a289d332ecd1109d"

        val api_service: RetrofitInterface by lazy {
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface::class.java)
        }
    }

    @GET("top-headlines")
    suspend fun getHeadlinesNews(
        @Query("country") country: String,
        @Query("category") category: String = "",
        @Query("pageSize") pageSize: String = "50",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsModel

    @GET("everything")
    suspend fun getSearchedNews(
        @Query("language") country: String,
        @Query("pageSize") pageSize: String = "100",
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsModel
}
