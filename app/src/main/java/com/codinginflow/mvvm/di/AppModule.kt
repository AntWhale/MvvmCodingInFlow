package com.codinginflow.mvvm.di

import com.codinginflow.mvvm.Config
import com.codinginflow.mvvm.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            //.baseUrl(UnsplashApi.BASE_URL)
            .baseUrl(Config.INSTANCE.apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi = retrofit.create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(Config.INSTANCE.connectTimeOut, TimeUnit.MILLISECONDS)
            .readTimeout(Config.INSTANCE.readTimeOut, TimeUnit.MILLISECONDS)
            .writeTimeout(Config.INSTANCE.writeTimeOut, TimeUnit.MILLISECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return client
            .addInterceptor(loggingInterceptor)
            .build()
    }


}