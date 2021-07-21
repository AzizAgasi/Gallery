package com.techdot.gallery.retrofit

import com.techdot.gallery.Constants.Companion.EXTRA
import com.techdot.gallery.Constants.Companion.FORMAT
import com.techdot.gallery.Constants.Companion.KEY
import com.techdot.gallery.Constants.Companion.METHOD
import com.techdot.gallery.Constants.Companion.NOJSO
import com.techdot.gallery.Constants.Companion.PAGE
import com.techdot.gallery.Constants.Companion.PER_PAGE
import com.techdot.gallery.models.PhotosResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiService {
    @GET("rest/?")
    suspend fun getPhotos(@Query(METHOD) method: String,
                          @Query(PER_PAGE) perPage: Int,
                          @Query(PAGE) page: Int,
                          @Query(KEY) key: String,
                          @Query(FORMAT) format: String,
                          @Query(NOJSO) nojsoCallback: Int,
                          @Query(EXTRA) extras: String
                          ): Response<PhotosResponse>
}