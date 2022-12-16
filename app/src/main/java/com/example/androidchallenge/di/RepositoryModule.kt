package com.example.androidchallenge.di

import com.example.androidchallenge.repository.AlbumRepository
import com.example.androidchallenge.repository.PhotoListRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { PhotoListRepository(get(),get()) }
    factory { AlbumRepository(get(),get()) }
}