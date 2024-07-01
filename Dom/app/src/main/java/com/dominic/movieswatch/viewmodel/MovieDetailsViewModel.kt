package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<Movie?>()
    val movie: MutableLiveData<Movie?> get() = _movieDetails

    fun getMovieDetails(title: String): MutableLiveData<Movie?> {
        viewModelScope.launch {
            val movie = repository.getMovieByTitle(title)
            _movieDetails.postValue(movie)
        }
        return movie
    }
}

class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
/*class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> get() = _movieDetails

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movieDetails = repository.getMovieDetails(movieId)
            _movieDetails.value = movieDetails
            _isFavorite.value = repository.isFavorite(movieId)
        }
    }

    fun toggleFavorite() {
        _movieDetails.value?.let { movie ->
            viewModelScope.launch {
                val newStatus = repository.toggleFavorite(movie.id)
                _isFavorite.value = newStatus
            }
        }
    }

//    fun loadTrailer(movieId: Int) {
//        viewModelScope.launch {
//            val trailer = repository.getMovieTrailer(movieId)
//            // Assuming you have a method to play the trailer
//            playTrailer(trailer.url)
//        }
//    }

    private fun playTrailer(url: String) {
        // Logic to play the trailer
    }
}*/