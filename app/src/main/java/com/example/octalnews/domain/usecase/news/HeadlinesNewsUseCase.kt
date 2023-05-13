package com.example.octalnews.domain.usecase.news

import com.example.octalnews.data.repository.DomainRepositoryImpl
import com.example.octalnews.domain.model.retrofit.news.NewsModel
import com.example.octalnews.domain.usecase.BaseUseCase
import javax.inject.Inject

class HeadlinesNewsUseCase @Inject constructor(private val domainRepositoryImpl: DomainRepositoryImpl) :
    BaseUseCase<NewsModel>() {
    override suspend fun invoke(value1: Any?, value2: Any?): NewsModel =
        domainRepositoryImpl.getHeadlinesNews(value1.toString(), value2.toString())
}