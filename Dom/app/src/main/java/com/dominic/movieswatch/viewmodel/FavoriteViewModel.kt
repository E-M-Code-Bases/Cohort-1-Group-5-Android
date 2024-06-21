package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MovieRepository) : ViewModel() {
   val favoriteMovies = repository.getFavoriteMovies()
//
//    fun addFavoriteMovie(movie: Movie) = viewModelScope.launch {
//        repository.addFavoriteMovie(movie)
//    }
//
//    fun removeFavoriteMovie(movie: Movie) = viewModelScope.launch {
//        repository.removeFavoriteMovie(movie)
//    }
}