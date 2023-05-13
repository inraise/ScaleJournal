package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String = "RUB"
    ): RatesModel
}