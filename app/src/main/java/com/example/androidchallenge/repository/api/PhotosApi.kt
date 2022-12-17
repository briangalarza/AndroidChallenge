package com.example.androidchallenge.repository.api

import com.example.androidchallenge.model.Album
import com.example.androidchallenge.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path


interface PhotosApi {

    @GET("albums/{id}/photos")
    suspend fun getPhotosList(@Path("id")albumId : String): List<Photo>

    @GET("albums")
    suspend fun getAlbums(): List<Album>

}