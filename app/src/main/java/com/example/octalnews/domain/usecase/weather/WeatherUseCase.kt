package com.example.octalnews.domain.usecase.weather

import com.example.octalnews.data.repository.DomainRepositoryImpl
import com.example.octalnews.domain.model.retrofit.weather.WeatherModel
import com.example.octalnews.domain.usecase.BaseUseCase
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val domainRepositoryImpl: DomainRepositoryImpl) :
    BaseUseCase<WeatherModel>() {
    override suspend fun invoke(value1: Any?, value2: Any?): WeatherModel =
        domainRepositoryImpl.getWeather(value1.toString().toDouble(), value2.toString().toDouble())
}