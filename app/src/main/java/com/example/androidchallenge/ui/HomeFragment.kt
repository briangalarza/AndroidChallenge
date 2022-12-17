package com.example.androidchallenge.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidchallenge.databinding.FragmentHomeBinding
import com.example.androidchallenge.model.Album
import com.example.androidchallenge.networking.Status
import com.example.androidchallenge.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val albumViewModel: AlbumViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* view.findViewById<Button>(R.id.button).setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_photosFragment)
        }*/
        initBindings()
        initObservers()
        albumViewModel.loadAlbums()
    }

    private fun initBindings() {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.albumsList.run {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun initObservers() {
       albumViewModel.getAlbums().observe(viewLifecycleOwner, Observer { result ->
           when (result.status) {
               Status.SUCCESS ->  {
                   renderList(result.data)
                   binding.homeAlbumsProgressContainer.visibility = View.GONE
                   binding.albumsList.visibility = View.VISIBLE
               }
               Status.ERROR -> {
                   binding.homeAlbumsProgressContainer.visibility = View.GONE
                   Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
               }
               Status.LOADING -> {
                   binding.homeAlbumsProgressContainer.visibility = View.VISIBLE
                   binding.albumsList.visibility = View.GONE
               }
           }
        })
    }

    private fun renderList(albums: List<Album>?) {
        if (!albums.isNullOrEmpty()) {
            setRecyclerData(albums)

        } else {
            showSnackBarMessage()
        }
    }


    private fun showSnackBarMessage() {
        //Snackbar.make(this, "No hay resultados", Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecyclerData(albums: List<Album>) {
            binding.albumsList.adapter = AlbumsAdapter(albums)

    }

}