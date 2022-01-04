package com.example.albumphotolist.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.albumphotolist.databinding.ItemPhotoDetailBinding
import com.example.albumphotolist.source.builder.RetrofitBuilder
import com.example.albumphotolist.source.dto.Photo
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response




class PhotoDetailActivity : AppCompatActivity() {

    private val _photos = mutableListOf<Photo>()
    private lateinit var binding: ItemPhotoDetailBinding
    private val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemPhotoDetailBinding.inflate(layoutInflater)
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
        val idRetrieved = bundle?.getString("idPhoto")
        val photosDetailsCall = RetrofitBuilder.apiService.getPhotos(idRetrieved)
        photosDetailsCall.enqueue(object : retrofit2.Callback<Photo> {
            override fun onResponse(
                call: Call<Photo>,
                response: Response<Photo>
            ) {
                Log.println(Log.ASSERT,"",response.body().toString())
                var photo : Photo? = null
                //response.body()?.let { _photos.addAll(it) }
                response.body()?.let {photo = it}
                binding.photoAlbumId.text = photo?.albumId.toString()
                binding.photoId.text = photo?.id.toString()
                binding.photoTitle.text = photo?.title
                Picasso.get().load(photo?.thumbnailUrl).into(binding.photoThumbnail)
                //fillPhoto(_photos)
                _photos.clear()
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                showError()
            }

        })
    }

        private fun fillPhoto(_photos: MutableList<Photo>) {
            binding.photoAlbumId.text = _photos.get(0).albumId.toString()
            binding.photoId.text = _photos.get(0).id.toString()
            binding.photoTitle.text = _photos.get(0).title
            Picasso.get().load(_photos.get(0).thumbnailUrl).into(binding.photoThumbnail)

        }


        private fun showError(){
        Toast.makeText(this,"Error", Toast.LENGTH_LONG)
    }
}