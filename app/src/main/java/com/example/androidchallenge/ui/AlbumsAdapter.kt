package com.example.androidchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.R
import com.example.androidchallenge.databinding.AlbumItemBinding
import com.example.androidchallenge.model.Album
import com.example.androidchallenge.utils.setImageFromUrl

class AlbumsAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlbumItemBinding.inflate(inflater, parent, false)
        return AlbumsViewHolder(binding)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    class AlbumsViewHolder(binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding = binding

        fun bind(album: Album) {
            binding.albumName.text = album.title
            //binding.executePendingBindings()
           binding.albumImg.setImageFromUrl(album.imgUrl)
            binding.root.setOnClickListener { view ->
               // val bundle = bundleOf(PhotoDetailsFragment.PHOTO_ARG to photo)
                view.findNavController().navigate(R.id.action_homeFragment_to_photosFragment)
            }
        }
    }
}