package com.example.androidchallenge.di

import com.example.androidchallenge.viewmodel.AlbumViewModel
import com.example.androidchallenge.viewmodel.PhotoListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { AlbumViewModel(get()) }
    factory { PhotoListViewModel(get()) }
}