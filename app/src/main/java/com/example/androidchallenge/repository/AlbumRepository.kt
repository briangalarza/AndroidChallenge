package com.example.androidchallenge.repository

import com.example.androidchallenge.model.Album
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.networking.ResponseHandler
import com.example.androidchallenge.repository.api.PhotosApi

class AlbumRepository(private val photosApi: PhotosApi, private val responseHandler: ResponseHandler) {
    suspend fun getAlbums(): Resource<List<Album>> {
        return try {
            val response = photosApi.getAlbums()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}