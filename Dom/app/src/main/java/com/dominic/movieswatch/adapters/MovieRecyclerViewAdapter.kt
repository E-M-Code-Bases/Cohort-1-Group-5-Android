package com.dominic.movieswatch.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.MovieItemsBinding
import com.dominic.movieswatch.model.Movie

private const val TAG = "MovieAdapter"

class MovieAdapter(private var movies: List<Movie>, private val onItemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            val url = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Log.d(TAG, "poster -> $url")
            Glide.with(binding.moviePoster.context)
                .load(url)
                .placeholder(R.drawable.baseline_image_search_24)
                .into(binding.moviePoster)

            binding.title.text = movie.title

            binding.root.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    // Public method to update movies dataset
    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
