package com.example.albumphotolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.databinding.ActivityMainBinding
import com.example.albumphotolist.source.api.APIService
import com.example.albumphotolist.source.builder.RetrofitBuilder
import com.example.albumphotolist.source.dto.Album
import com.example.albumphotolist.ui.AlbumAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: AlbumAdapter
    private val _albums = mutableListOf<Album>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAlbums()
    }


    private fun showAlbums(){
            CoroutineScope(Dispatchers.IO).launch {
                _albums.clear()
                val albumsResponse = RetrofitBuilder.retrofit.getAlbums()
                runOnUiThread {
                    if (albumsResponse.isNotEmpty()) {
                        _albums.addAll(albumsResponse)
                        adapter = AlbumAdapter(_albums)
                        binding.recyclerViewMain.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        showError()
                    }
                }
            }
        }


    private fun showError(){
        Toast.makeText(this,"Error", Toast.LENGTH_LONG)
    }

   /* private fun showPhotos(query : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPhotos("$query/photos")
            val puppies = call.body()
            if(call.isSuccessful){
                //show Recyclerview
            }else{
                //show error
            }
        }
    }

    private fun showPhotoDetails(id : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPhotos("photos/$id")
            val puppies = call.body()
            if(call.isSuccessful){
                //show Recyclerview
            }else{
                //show error
            }
        }
    }*/
}