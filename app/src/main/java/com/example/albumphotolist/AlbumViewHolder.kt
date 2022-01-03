package com.example.albumphotolist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.databinding.ItemAlbumBinding

class AlbumViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemAlbumBinding.bind(view)

    fun bind (album: Album){
        binding.albumUserId.setText(album.userId)
        binding.albumId.setText(album.id)
        binding.albumTitle.text = album.title
    }
}