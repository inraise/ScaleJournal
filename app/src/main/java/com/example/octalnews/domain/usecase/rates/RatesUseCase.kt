package com.example.octalnews.domain.usecase.rates

import com.example.octalnews.data.repository.DomainRepositoryImpl
import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import com.example.octalnews.domain.usecase.BaseUseCase
import javax.inject.Inject

class RatesUseCase @Inject constructor(private val domainRepositoryImpl: DomainRepositoryImpl) :
    BaseUseCase<RatesModel>() {
    override suspend fun invoke(value1: Any?, value2: Any?): RatesModel =
        domainRepositoryImpl.getRates()
}