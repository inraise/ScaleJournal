package com.example.octalnews.presentation.screen.viewmodels

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.data.ReadData
import com.example.octalnews.domain.model.retrofit.NewsModel
import com.example.octalnews.data.remote.RetrofitInterface
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    var allNews by mutableStateOf(NewsModel())
    var businessNews by mutableStateOf(NewsModel())
    var scienceNews by mutableStateOf(NewsModel())
    var technologyNews by mutableStateOf(NewsModel())
    var healthNews by mutableStateOf(NewsModel())

    var searchedNews by mutableStateOf(NewsModel())

    fun getNews(category: String, context: Activity) {
        viewModelScope.launch {
            try {
                allNews = RetrofitInterface.api_service.getHeadlinesNews(
                    category = category,
                    country = ReadData("country", context)
                )
                businessNews = RetrofitInterface.api_service.getHeadlinesNews(
                    category = "business",
                    country = ReadData("country", context)
                )
                scienceNews = RetrofitInterface.api_service.getHeadlinesNews(
                    category = "science",
                    country = ReadData("country", context)
                )
                technologyNews =
                    RetrofitInterface.api_service.getHeadlinesNews(
                        category = "technology",
                        country = ReadData("country", context)
                    )
                healthNews = RetrofitInterface.api_service.getHeadlinesNews(
                    category = "health",
                    country = ReadData("country", context)
                )
            } catch (_: java.lang.Exception) {
            }
        }
    }

    fun getSearchedNews(searchText: String, context: Activity) {
        viewModelScope.launch {
            try {
                searchedNews = RetrofitInterface.api_service.getSearchedNews(
                    q = searchText,
                    country = ReadData("lang", context)
                )
            } catch (_: Exception) {
            }
        }
    }
}