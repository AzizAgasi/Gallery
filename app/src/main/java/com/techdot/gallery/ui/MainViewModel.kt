package com.techdot.gallery.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techdot.gallery.data.PhotoRepository
import com.techdot.gallery.models.Photo
import com.techdot.gallery.models.PhotosResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): ViewModel(){

    private val _photos = MutableLiveData<PhotosResponse>()

    val photos: LiveData<PhotosResponse>
        get() = _photos

    private val _photoList = MutableLiveData<List<Photo>>()

    val photoList: LiveData<List<Photo>>
        get() = _photoList

    init {
        getPhotos()
    }

    private fun getPhotos() = viewModelScope.launch {
        val response = photoRepository.getPhotoResponse()
        when (response.isSuccessful) {
            true -> {
                with(response.body()) {
                    _photos.value = this
                    _photoList.value = _photos.value?.photos?.photo
                }
            }
        }
    }
}