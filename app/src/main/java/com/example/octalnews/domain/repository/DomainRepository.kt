package com.example.octalnews.domain.repository

import com.example.octalnews.domain.model.retrofit.news.NewsModel
import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import com.example.octalnews.domain.model.retrofit.weather.WeatherModel

interface DomainRepository {

    suspend fun getHeadlinesNews(country: String, category: String = ""): NewsModel
    suspend fun getSearchedNews(country: String, q: String): NewsModel

    suspend fun getWeather(lat: Double, lon: Double): WeatherModel

    suspend fun getRates(): RatesModel
}