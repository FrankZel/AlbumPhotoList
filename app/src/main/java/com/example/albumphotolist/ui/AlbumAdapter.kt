package com.example.albumphotolist.ui

import android.app.AlertDialog
import android.app.TaskInfo
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.albumphotolist.MainActivity
import com.example.albumphotolist.R
import com.example.albumphotolist.databinding.ItemAlbumBinding
import com.example.albumphotolist.source.dto.Album

class AlbumAdapter(private val albums: List<Album>, val contextActivity: Context) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view,contextActivity)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])


    }

    override fun getItemCount() = albums.size


    class AlbumViewHolder(view: View, private val context: Context):
        RecyclerView.ViewHolder(view) {
        private val binding = ItemAlbumBinding.bind(view)
        fun bind (album: Album){
            binding.albumUserId.text = album.userId.toString()
            binding.albumId.text = album.id.toString()
            binding.albumTitle.text = album.title
            itemView.setOnClickListener{
                val value = album.userId.toString()
                context.startActivity(Intent(context,PhotoActivity::class.java).putExtra("idAlbum",value))
            }
        }


    }
}
