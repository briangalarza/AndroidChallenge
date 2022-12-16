package com.example.androidchallenge.repository.api

import com.example.androidchallenge.model.Album
import com.example.androidchallenge.model.Photo


interface PhotosApi {

    suspend fun getPhotosList(): List<Photo>

    suspend fun getAlbums(): List<Album>

}