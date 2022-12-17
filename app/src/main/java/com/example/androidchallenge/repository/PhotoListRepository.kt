package com.example.androidchallenge.repository

import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.networking.ResponseHandler
import com.example.androidchallenge.repository.api.PhotosApi

class PhotoListRepository(private val photosApi: PhotosApi, private val responseHandler: ResponseHandler) {
    suspend fun getPhotoList(albumId: String): Resource<List<Photo>> {
        return try {
            val response = photosApi.getPhotosList(albumId)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}