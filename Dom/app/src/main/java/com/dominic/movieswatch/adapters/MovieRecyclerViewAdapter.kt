package com.dominic.movieswatch.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.MovieItemsBinding
import com.dominic.movieswatch.model.Movie

class MovieAdapter(private var movies: List<Movie>) :

    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieItemsBinding) :

        RecyclerView.ViewHolder(binding.root) {

        //fetchmovies
        fun bind(movie: Movie) {
//            Glide.with(binding.moviePoster.context)
//                .load(movie.posterPath)
//                .into(binding.moviePoster)
            binding.title.text = movie.title
            binding.root.setOnClickListener {

                val bundle = Bundle().apply {
                    putString("movie", movie.title)
                }

                it.findNavController()
                    .navigate(R.id.action_global_movieDetails, bundle)
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



