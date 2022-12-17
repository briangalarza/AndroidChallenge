package com.example.androidchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidchallenge.model.Album
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository) :ViewModel() {

    private val albums = MutableLiveData<Resource<List<Album>>>()

    fun getAlbums() = albums

    fun loadAlbums() {
        albums.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = albumRepository.getAlbums()
            response.let {
                albums.postValue(it)
            }
        }
    }

}