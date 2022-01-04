package com.example.albumphotolist.source.api
import com.example.albumphotolist.source.dto.Album
import com.example.albumphotolist.source.dto.Photo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotosByAlbum(@Path("id") albumId : String?): Call<List<Photo>>

}