package com.dominic.movieswatch.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.FragmentMovieDetailsBinding
import com.dominic.movieswatch.repository.MovieDetailsRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.viewmodel.MovieDetailsViewModel
import com.dominic.movieswatch.viewmodel.MovieDetailsViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieDetailsRepo(API_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieTitle = args.movie.title

        viewModel.getMovieDetails(movieTitle).observe(viewLifecycleOwner) { movie ->
            movie?.let {
                binding.movie = it
                val url = "https://image.tmdb.org/t/p/w500" + it.posterPath
                Glide.with(this)
                    .load(url)
                    .into(binding.moviePoster)
                //cl.. ls here
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            val favoriteIconRes = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.favoriteIcon.setImageResource(favoriteIconRes)
        }

        binding.favoriteIcon.setOnClickListener {
            val movie = binding.movie
            if (movie != null) {
                viewModel.toggleFavorite(movie)

            }
        }
        return binding.root
    }

    private fun setupYouTubePlayerView(trailerId: String?) {
        val youTubePlayerView: YouTubePlayerView = binding.trailerView

        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Log.d("YouTubePlayer", "YouTubePlayer is ready")
                trailerId?.let {
                    youTubePlayer.cueVideo(it, 0f)
                }
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                Log.e("YouTubePlayerError", "Error: $error")
            }
        })


        youTubePlayerView.setOnClickListener {
            youTubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    Log.d("YouTubePlayer", "Playing video")
                    youTubePlayer.play()
                }
            })
        }
    }
}

