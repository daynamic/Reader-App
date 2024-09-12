package com.akshat.readerapp.di

import com.akshat.readerapp.network.BooksApi
import com.akshat.readerapp.repository.BookRepository
import com.akshat.readerapp.utils.Constants.BASE_URL
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
object AppModule {


    @Singleton
    @Provides
    fun provideBookRepository(api: BooksApi) = BookRepository(api)

    @Singleton
    @Provides
    fun provideBookApi(): BooksApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(BooksApi::class.java)
    }
}