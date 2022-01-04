package com.example.albumphotolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.R
import com.example.albumphotolist.databinding.ItemAlbumBinding
import com.example.albumphotolist.source.dto.Album

class AlbumAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(layoutInflater.inflate(R.layout.item_album, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])

    }

    override fun getItemCount() = albums.size


    class AlbumViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemAlbumBinding.bind(view)
        fun bind (album: Album){
            binding.albumUserId.setText(album.userId.toString())
            binding.albumId.setText(album.id.toString())
            binding.albumTitle.text = album.title
        }
    }
}