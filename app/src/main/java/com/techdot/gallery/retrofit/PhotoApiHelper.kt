package com.techdot.gallery.retrofit

import com.techdot.gallery.models.PhotosResponse
import retrofit2.Call
import retrofit2.Response

interface PhotoApiHelper {
    suspend fun getPhotos(): Response<PhotosResponse>
}