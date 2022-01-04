package com.example.albumphotolist.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.R
import com.example.albumphotolist.databinding.ItemPhotoBinding
import com.example.albumphotolist.databinding.ItemPhotoDetailBinding
import com.example.albumphotolist.source.dto.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photos : List<Photo>, private val context : Context):
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    class PhotoViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPhotoBinding.bind(view)
        fun bind(photo: Photo) {
            Picasso.get().load(photo.url).into((binding.image))

            itemView.setOnClickListener {
                val value = photo.id.toString()
                Log.println(Log.ASSERT,"",value.toString())

                context.startActivity(
                    Intent(context, PhotoDetailActivity::class.java).putExtra(
                        "idPhoto",
                        value
                    )
                )
            }
        }

    }
}