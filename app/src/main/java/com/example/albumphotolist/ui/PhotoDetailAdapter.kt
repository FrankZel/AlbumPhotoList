package com.example.albumphotolist.ui

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.R
import com.example.albumphotolist.databinding.ItemPhotoBinding
import com.example.albumphotolist.databinding.ItemPhotoDetailBinding
import com.example.albumphotolist.source.dto.Photo
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class PhotoDetailAdapter(private val photoDetails : List<Photo>, val context : Context):
    RecyclerView.Adapter<PhotoDetailAdapter.PhotoDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoDetailViewHolder(layoutInflater.inflate(R.layout.item_photo_detail, parent, false), context)
    }

    override fun onBindViewHolder(holder: PhotoDetailViewHolder, position: Int) {
        val item = photoDetails[position]
        holder.bind(item)
    }

    override fun getItemCount() = photoDetails.size

    class PhotoDetailViewHolder(view : View, private val context: Context) : RecyclerView.ViewHolder(view){
        private val binding = ItemPhotoDetailBinding.bind(view)

        fun bind(photoDetail : Photo){
            binding.photoAlbumId.text = photoDetail.albumId.toString()
            binding.photoId.text = photoDetail.id.toString()
            Picasso.get().load(photoDetail.thumbnailUrl).into(binding.photoThumbnail)
            binding.photoTitle.text = photoDetail.title

            itemView.setOnClickListener{
                val value = photoDetail.id.toString()
                context.startActivity(Intent(context, PhotoActivity::class.java).putExtra("idPhoto", value))
            }
        }
    }
}