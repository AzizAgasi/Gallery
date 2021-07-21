package com.techdot.gallery.data

import com.techdot.gallery.retrofit.PhotoApiHelper
import javax.inject.Inject


class PhotoRepository @Inject constructor(
    private val apiHelper: PhotoApiHelper
) {

    suspend fun getPhotoResponse() = apiHelper.getPhotos()
}