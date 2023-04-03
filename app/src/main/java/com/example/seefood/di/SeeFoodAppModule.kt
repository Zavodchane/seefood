package com.example.seefood.di

import android.app.Application
import com.example.seefood.data.network.ApiService
import com.example.seefood.database.SeeFoodDatabase
import com.example.seefood.database.dao.DishDao
import com.example.seefood.database.repos.DishRepository
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

   // TODO: Добавить базовую ссылку, возможно только после создания API (Влад момент :) )
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


    @Provides
    @Singleton
    fun provideSeeFoodDatabase(app: Application) : SeeFoodDatabase {
        return SeeFoodDatabase.getInstance(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideDishRepository(
        dishDao: DishDao
    ) : DishRepository {
        return DishRepository(dishDao = dishDao)
    }

    @Provides
    @Singleton
    fun provideDishDao(seeFoodDatabase: SeeFoodDatabase) : DishDao {
        return seeFoodDatabase.dishDao
    }
}