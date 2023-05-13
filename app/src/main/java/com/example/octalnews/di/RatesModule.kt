package com.example.octalnews.di

import com.example.octalnews.data.remote.RatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RatesModule {

    @Provides
    @Singleton
    fun ratesProvide(): RatesApi {
        return Retrofit.Builder().baseUrl("https://api.exchangerate.host/").addConverterFactory(
            GsonConverterFactory.create())
            .build().create()
    }
}