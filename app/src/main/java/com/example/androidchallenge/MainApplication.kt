package com.example.androidchallenge

import android.app.Application
import com.example.androidchallenge.di.networkModule
import com.example.androidchallenge.di.repositoryModule
import com.example.androidchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
    }
}