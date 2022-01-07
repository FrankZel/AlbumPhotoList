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
import com.example.albumphotolist.databinding.ItemPhotoBinding
import com.example.albumphotolist.source.dto.Album
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response


class PhotoActivity : AppCompatActivity() {

    private val _photos = mutableListOf<Photo>()
    private lateinit var binding: ItemPhotoBinding
    private val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showPhoto()

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
    private fun showPhoto(){
        val bundle : Bundle? = intent.extras
            val idRetrieved = bundle?.getString("idPhoto")
            _photos.clear()
            val photoCall = RetrofitBuilder.apiService.getPhotos(idRetrieved)
            photoCall.enqueue(object : retrofit2.Callback<Photo> {
                override fun onResponse(
                    call: Call<Photo>,
                    response: Response<Photo>
                ) {
                    Log.println(Log.ASSERT,"",response.body().toString())
                    Log.println(Log.ASSERT,"",idRetrieved.toString())
                    var photo : Photo? = null
                    response.body()?.let {photo = it}
                    binding.phTitle.text = photo?.title
                    Picasso.get().load(photo?.url).into(binding.image)
                }

                override fun onFailure(call: Call<Photo>, t: Throwable) {
                    showError()
                }

            })
        }



    private fun showError(){
        Toast.makeText(this,"Error", Toast.LENGTH_LONG)
    }
}