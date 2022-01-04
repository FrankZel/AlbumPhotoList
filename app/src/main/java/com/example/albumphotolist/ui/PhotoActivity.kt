package com.example.albumphotolist.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.albumphotolist.databinding.ActivityPhotoBinding
import com.example.albumphotolist.source.builder.RetrofitBuilder
import com.example.albumphotolist.source.dto.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.albumphotolist.source.dto.Album
import retrofit2.Call
import retrofit2.Response


class PhotoActivity : AppCompatActivity() {

    lateinit var adapter: PhotoAdapter
    private val _photos = mutableListOf<Photo>()
    private lateinit var binding: ActivityPhotoBinding
    private val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PhotoAdapter(emptyList(), this )
        binding.recyclerViewPhotos.adapter = adapter
        showPhotosByAlbum()

    }

    /*runOnUiThread{
        if (photosResponse.isNotEmpty()) {
            _photos.addAll(photosResponse)
            adapter = PhotoAdapter(_photos, context)
            binding.recyclerViewPhotos.adapter = adapter
            adapter.notifyDataSetChanged()

        } else {
            showError()
        }
    }*/
    private fun showPhotosByAlbum(){
        val bundle : Bundle? = intent.extras
            val idRetrieved = bundle?.getString("idAlbum")
            _photos.clear()
            val photosCall = RetrofitBuilder.apiService.getPhotosByAlbum(idRetrieved)
            photosCall.enqueue(object : retrofit2.Callback<List<Photo>> {
                override fun onResponse(
                    call: Call<List<Photo>>,
                    response: Response<List<Photo>>
                ) {
                    Log.println(Log.ASSERT,"",response.body().toString())
                    Log.println(Log.ASSERT,"",idRetrieved.toString())

                    response.body()?.let { _photos.addAll(it) }
                    adapter = PhotoAdapter(_photos, context)
                    binding.recyclerViewPhotos.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    showError()
                }

            })
        }



    private fun showError(){
        Toast.makeText(this,"Error", Toast.LENGTH_LONG)
    }
}