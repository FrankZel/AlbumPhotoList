package com.example.albumphotolist.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.albumphotolist.databinding.ActivityPhotoDetailBinding
import com.example.albumphotolist.databinding.ItemPhotoDetailBinding
import com.example.albumphotolist.source.builder.RetrofitBuilder
import com.example.albumphotolist.source.dto.Photo
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response




class PhotoDetailActivity : AppCompatActivity() {

    lateinit var adapter: PhotoDetailAdapter
    private val _photos = mutableListOf<Photo>()
    private lateinit var binding: ActivityPhotoDetailBinding
    private val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        adapter = PhotoDetailAdapter(emptyList(), context)
        binding.recyclerViewPhotoDetail.adapter = adapter
        setContentView(binding.root)
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
        val photosDetailsCall = RetrofitBuilder.apiService.getPhotosByAlbum(idRetrieved)
        photosDetailsCall.enqueue(object : retrofit2.Callback<List<Photo>> {
            override fun onResponse(
                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                Log.println(Log.ASSERT,"",response.body().toString())
                Log.println(Log.ASSERT,"","estoy aca")

                //var photo : Photo? = null
                response.body()?.let { _photos.addAll(it) }
                //response.body()?.let {photo = it}
                adapter = PhotoDetailAdapter(_photos, context)
                binding.recyclerViewPhotoDetail.adapter = adapter
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