package com.example.albumphotolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showAlbums(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAlbums("albums")
            val puppies = call.body()
            if(call.isSuccessful){
                //show Recyclerview
            }else{
                //show error
            }
        }
    }

    private fun showPhotos(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPhotos("photos")
            val puppies = call.body()
            if(call.isSuccessful){
                //show Recyclerview
            }else{
                //show error
            }
        }
    }

    private fun showPhotoDetails(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPhotos("photos")
            val puppies = call.body()
            if(call.isSuccessful){
                //show Recyclerview
            }else{
                //show error
            }
        }
    }
}