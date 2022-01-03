package com.example.albumphotolist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAlbums(@Url url: String): Response<Album>

    @GET
    suspend fun getPhotos(@Url url: String): Response<Photos>

}