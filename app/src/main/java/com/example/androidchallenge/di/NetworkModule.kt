package com.example.androidchallenge.di

import com.example.androidchallenge.BuildConfig
import com.example.androidchallenge.networking.ResponseHandler
import com.example.androidchallenge.repository.api.PhotosApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { providePhotosApi(get()) }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}


fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

fun providePhotosApi(retrofit: Retrofit): PhotosApi = retrofit.create(PhotosApi::class.java)


