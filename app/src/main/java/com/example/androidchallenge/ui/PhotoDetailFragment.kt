package com.example.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidchallenge.databinding.FragmentPhotoDetailBinding
import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.utils.setImageFromUrl
import com.example.androidchallenge.utils.zoomImageFromThumb


class PhotoDetailFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailBinding
    private lateinit var photo: Photo
    companion object {
        const val PHOTO_ARG = "PHOTO_ARG"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<Photo>(PHOTO_ARG)?.let {
            photo = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoDetailsImageZoom.setImageFromUrl(photo.thumbnail)
        with(binding.photoDetailsImage) {
            setImageFromUrl(photo.thumbnail)
            setOnClickListener {
                zoomImageFromThumb(binding.photoDetailsImageZoom,
                    binding.photoDetailsContainer,
                    200)
            }
        }
        binding.photoDetailsCameraTitle.text = photo.title
    }
}