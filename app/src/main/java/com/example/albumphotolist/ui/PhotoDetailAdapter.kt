package com.example.albumphotolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.R
import com.example.albumphotolist.databinding.ItemPhotoDetailBinding
import com.example.albumphotolist.source.dto.Photo
import com.squareup.picasso.Picasso

class PhotoDetailAdapter(private val photoDetails : List<Photo>):
    RecyclerView.Adapter<PhotoDetailAdapter.PhotoDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoDetailViewHolder(layoutInflater.inflate(R.layout.item_photo_detail, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoDetailViewHolder, position: Int) {
        val item = photoDetails[position]
        holder.bind(item)
    }

    override fun getItemCount() = photoDetails.size

    class PhotoDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = ItemPhotoDetailBinding.bind(view)

        fun bind(photoDetail : Photo){
            binding.photoAlbumId.setText(photoDetail.albumId)
            binding.photoId.setText(photoDetail.id)
            Picasso.get().load(photoDetail.thumbnailUrl).into(binding.photoThumbnail)
            binding.photoTitle.text = photoDetail.title
        }
    }
}