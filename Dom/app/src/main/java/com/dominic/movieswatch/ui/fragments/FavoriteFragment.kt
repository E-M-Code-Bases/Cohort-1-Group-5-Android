package com.dominic.movieswatch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dominic.movieswatch.adapters.MovieAdapter
import com.dominic.movieswatch.databinding.FragmentFavoriteBinding
import com.dominic.movieswatch.repository.FavoritesRepository
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.account_id
import com.dominic.movieswatch.viewmodel.FavoriteMoviesViewModelFactory
import com.dominic.movieswatch.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteMoviesViewModelFactory(FavoritesRepository(API_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = MovieAdapter(listOf()) { movie ->
            // Handle movie click
        }

        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.favoriteRecyclerView.adapter = adapter

        viewModel.favoriteMovies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                adapter.movies = it
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.fetchFavoriteMovies(account_id, "Bearer $API_KEY")

        return binding.root
    }
}