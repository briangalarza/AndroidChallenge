package com.example.androidchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.repository.PhotoListRepository
import kotlinx.coroutines.launch

class PhotoListViewModel(private val photoListRepository: PhotoListRepository) : ViewModel() {

    private val photos = MutableLiveData<Resource<List<Photo>>>()

    fun getPhotos() = photos

    fun loadPhotos(albumId : String) {
        photos.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = photoListRepository.getPhotoList(albumId)
            response.let {
                photos.postValue(it)
            }
        }
    }

}