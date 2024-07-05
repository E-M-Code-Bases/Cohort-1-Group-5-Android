package com.dominic.movieswatch.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.MovieItemsBinding
import com.dominic.movieswatch.model.Movie

private const val TAG = "adapter"

class MovieAdapter(var movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) :

    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieItemsBinding) :

        RecyclerView.ViewHolder(binding.root) {

        //fetchmovies
        fun bind(movie: Movie) {
            val url = "https://image.tmdb.org/t/p/w500" + movie.posterPath
            Log.d(TAG, "poster -> $url")
            Glide.with(binding.moviePoster.context).load(url)
                .placeholder(R.drawable.baseline_image_search_24).into(binding.moviePoster)
            binding.title.text = movie.title
            binding.root.setOnClickListener {

                onMovieClick(movie)


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //val movie = movies[position]
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}



