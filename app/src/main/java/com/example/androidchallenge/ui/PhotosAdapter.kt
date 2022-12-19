package com.example.androidchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.R
import com.example.androidchallenge.databinding.PhotoItemBinding
import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.utils.setImageFromUrl

class PhotosAdapter(private val photos: List<Photo>) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(binding)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    class PhotosViewHolder(binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding = binding

        fun bind(photo: Photo) {

            binding.photoImg.setImageFromUrl(photo.thumbnail)
            binding.root.setOnClickListener { view ->
                val bundle = bundleOf(PhotoDetailFragment.PHOTO_ARG to photo)
                view.findNavController().navigate(R.id.action_photosFragment_to_photoDetailFragment,bundle)
            }
        }
    }
}