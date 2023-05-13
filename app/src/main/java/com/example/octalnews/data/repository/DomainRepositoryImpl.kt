package com.example.octalnews.data.repository

import android.util.Log
import com.example.octalnews.data.remote.NewsApi
import com.example.octalnews.data.remote.RatesApi
import com.example.octalnews.data.remote.WeatherApi
import com.example.octalnews.domain.model.retrofit.news.NewsModel
import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import com.example.octalnews.domain.repository.DomainRepository
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val weatherApi: WeatherApi,
    private val ratesApi: RatesApi
): DomainRepository {
    override suspend fun getHeadlinesNews(country: String, category: String): NewsModel {
        return newsApi.getHeadlinesNews(country = country, category = category)
    }

    override suspend fun getSearchedNews(country: String, q: String): NewsModel {
        return newsApi.getSearchedNews(country = country, q = q)
    }

    override suspend fun getWeather(lat: Double, lon: Double): WeatherModel {
        return weatherApi.getWeather(lat = lat, lon = lon)
    }

    override suspend fun getRates(): RatesModel {
        return ratesApi.getRates()
    }
}