package com.example.seefood.di

import com.example.seefood.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeeFoodAppModule {

    // TODO: Добавить базовую ссылку возможно только после создания API (Влад момент :) )
    @Provides
    fun baseUrl() = ""

    // Это пока что лучше не трогать, потому что конвертер может поменяться как и базовая ссылка
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String) : ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}