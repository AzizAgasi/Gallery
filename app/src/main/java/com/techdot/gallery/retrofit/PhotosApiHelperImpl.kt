package com.techdot.gallery.retrofit

import com.techdot.gallery.models.PhotosResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class PhotosApiHelperImpl @Inject constructor(
    private val apiService: PhotosApiService
): PhotoApiHelper {

    override suspend fun getPhotos(): Response<PhotosResponse> =
        apiService.getPhotos(
            "flickr.photos.getRecent",
            20,
            1,
            "6f102c62f41998d151e5a1b48713cf13",
            "json",
            1,
            "url_s"
        )
}