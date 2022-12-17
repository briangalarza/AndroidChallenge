package com.example.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidchallenge.databinding.FragmentPhotosBinding
import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.networking.Status
import com.example.androidchallenge.viewmodel.PhotoListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotosFragment : Fragment() {

    private val photosViewModel: PhotoListViewModel by viewModel()
    private lateinit var binding: FragmentPhotosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBindings()
        initObservers()
        photosViewModel.loadPhotos("1")
    }

    private fun initBindings() {
        val gridLayoutManager = GridLayoutManager(activity,3)
        binding.photoList.run {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
    }

    private fun initObservers() {
        photosViewModel.getPhotos().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS ->  {
                    renderList(result.data)
                    binding.photosProgressContainer.visibility = View.GONE
                    binding.photoList.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.photosProgressContainer.visibility = View.GONE
                    Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.photosProgressContainer.visibility = View.VISIBLE
                    binding.photoList.visibility = View.GONE
                }
            }
        })
    }

    private fun renderList(photos: List<Photo>?) {
        if (!photos.isNullOrEmpty()) {
            setRecyclerData(photos)

        } else {
            showSnackBarMessage()
        }
    }


    private fun showSnackBarMessage() {
        //Snackbar.make(this, "No hay resultados", Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecyclerData(photos: List<Photo>) {
        binding.photoList.adapter = PhotosAdapter(photos)

    }

}