package com.example.albumphotolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.example.albumphotolist.databinding.ActivityMainBinding
import com.example.albumphotolist.source.builder.RetrofitBuilder
import com.example.albumphotolist.source.dto.Album
import com.example.albumphotolist.ui.AlbumAdapter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var adapter: AlbumAdapter
    private val _albums = mutableListOf<Album>()
    private lateinit var binding: ActivityMainBinding
    private val context : Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAlbums()
    }

   /* if (albumsCall.isNotEmpty()) {
        _albums.addAll(albumsCall)
        adapter = AlbumAdapter(_albums, context)
        binding.recyclerViewMain.adapter = adapter
        adapter.notifyDataSetChanged()
    } else {
        showError()
    }*/
    private fun showAlbums(){
                _albums.clear()
                val albumsCall = RetrofitBuilder.apiService.getAlbums()
                albumsCall.enqueue(object : retrofit2.Callback<List<Album>> {
                    override fun onResponse(
                        call: Call<List<Album>>,
                        response: Response<List<Album>>
                    ) {
                        response.body()?.let { _albums.addAll(it) }
                        adapter = AlbumAdapter(_albums, context)
                        binding.recyclerViewMain.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                        showError()
                    }

                })
    }



    private fun showError(){
        Toast.makeText(this,"Error", Toast.LENGTH_LONG)
    }
}