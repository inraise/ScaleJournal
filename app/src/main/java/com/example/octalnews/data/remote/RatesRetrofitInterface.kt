package com.example.octalnews.data.remote

import com.example.octalnews.domain.model.retrofit.rates.RatesModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesRetrofitInterface {
    companion object {
        const val BASE_URL = "https://api.exchangerate.host/"

        val rates_api_service: RatesRetrofitInterface by lazy {
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(RatesRetrofitInterface::class.java)
        }
    }

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String = "RUB"
    ): RatesModel
}