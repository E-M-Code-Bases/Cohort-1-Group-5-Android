package com.dominic.movieswatch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dominic.movieswatch.databinding.FragmentMovieDetailsBinding
import com.dominic.movieswatch.repository.MovieRepository
import com.dominic.movieswatch.viewmodel.MovieDetailsViewModel
import com.dominic.movieswatch.viewmodel.MovieDetailsViewModelFactory

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieRepository(apikey = String())) // Pass the repository instance
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieTitle = args.movieTitle

        viewModel.getMovieDetails(movieTitle).observe(viewLifecycleOwner) { movie ->
            movie?.let {
                binding.movie = it
                val url = "https://image.tmdb.org/t/p/w500" + it.poster_path
                Glide.with(this)
                    .load(url)
                    .into(binding.moviePoster)
            }
        }

        return binding.root
    }
}
