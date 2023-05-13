package com.example.octalnews.presentation.screen.viewmodels

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.octalnews.data.ReadData
import com.example.octalnews.domain.model.retrofit.news.NewsModel
import com.example.octalnews.domain.usecase.news.HeadlinesNewsUseCase
import com.example.octalnews.domain.usecase.news.SearchedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val headlinesNewsUseCase: HeadlinesNewsUseCase,
    private val searchedNewsUseCase: SearchedNewsUseCase
) : ViewModel() {
    var allNews by mutableStateOf(NewsModel())
    var businessNews by mutableStateOf(NewsModel())
    var scienceNews by mutableStateOf(NewsModel())
    var technologyNews by mutableStateOf(NewsModel())
    var healthNews by mutableStateOf(NewsModel())

    var searchedNews by mutableStateOf(NewsModel())

    fun getNews(category: String, context: Activity) {
        viewModelScope.launch {
            try {

                allNews = headlinesNewsUseCase.invoke(
                    value2 = category,
                    value1 = ReadData("country", context)
                )

                businessNews = headlinesNewsUseCase.invoke(
                    value2 = "business",
                    value1 = ReadData("country", context)
                )

                scienceNews = headlinesNewsUseCase.invoke(
                    value2 = "science",
                    value1 = ReadData("country", context)
                )

                technologyNews =
                    headlinesNewsUseCase.invoke(
                        value2 = "technology",
                        value1 = ReadData("country", context)
                    )

                healthNews = headlinesNewsUseCase.invoke(
                    value2 = "health",
                    value1 = ReadData("country", context)
                )

            } catch (_: java.lang.Exception) {
            }
        }
    }

    fun getSearchedNews(searchText: String, context: Activity) {
        viewModelScope.launch {
            try {
                searchedNews = searchedNewsUseCase.invoke(
                    value2 = searchText,
                    value1 = ReadData("lang", context)
                )
            } catch (_: Exception) {
            }
        }
    }
}