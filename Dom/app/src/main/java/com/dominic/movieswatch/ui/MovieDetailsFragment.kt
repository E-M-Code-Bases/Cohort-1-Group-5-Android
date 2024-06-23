package com.dominic.movieswatch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.FragmentMovieDetailsBinding
import com.dominic.movieswatch.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind ViewModel to layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Get the movie ID from arguments and load movie details
        val movieId = args.movieId
        viewModel.loadMovieDetails(movieId)

        // Observe the movie data and update UI accordingly
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer { movie ->
            binding.movie = movie
        })

        // Observe the favorite status and update UI accordingly
        viewModel.isFavorite.observe(viewLifecycleOwner, Observer { isFavorite ->
            updateFavoriteIcon(isFavorite)
        })

        // Set click listener for the favorite icon
        binding.favoriteIcon.setOnClickListener {
            viewModel.toggleFavorite()
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val iconRes = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        binding.favoriteIcon.setImageResource(iconRes)
    }
}

