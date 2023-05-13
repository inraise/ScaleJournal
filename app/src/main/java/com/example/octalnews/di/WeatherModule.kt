package com.example.octalnews.di

import com.example.octalnews.data.remote.NewsApi
import com.example.octalnews.data.remote.WeatherApi
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
object WeatherModule {


    @Provides
    @Singleton
    fun weatherProvide(): WeatherApi {
        return Retrofit.Builder().baseUrl("https://api.open-meteo.com/").addConverterFactory(
            GsonConverterFactory.create()
        )
            .build().create()
    }
}
