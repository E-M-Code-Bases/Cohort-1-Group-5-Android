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
class MovieAdapter(private val movies: (Any) -> Unit) :

    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieItemsBinding) :

        RecyclerView.ViewHolder(binding.root) {

        //fetchmovies
        fun bind(movie: Movie) {
            Glide.with(binding.moviePoster.context)
                .load(movie.posterPath)
                .into(binding.moviePoster)
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


/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.MovieItemsBinding
import com.dominic.movieswatch.model.Movie

class MovieAdapter(private val onItemClick: (Movie) -> Unit) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: MovieItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_items,
            parent,
            false
        )
        return MovieViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(private val binding: MovieItemsBinding, private val onItemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.clickListener = View.OnClickListener { onItemClick(movie) }
            binding.executePendingBindings()
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
*/

