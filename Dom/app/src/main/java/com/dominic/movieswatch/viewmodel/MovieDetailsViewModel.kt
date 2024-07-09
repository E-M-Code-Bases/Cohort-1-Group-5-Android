package com.dominic.movieswatch.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.TrailerResult
import com.dominic.movieswatch.repository.MovieDetailsRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.account_id
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepo) : ViewModel() {

    private val _movieDetails = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> get() = _movieDetails

    private val _trailers = MutableLiveData<List<TrailerResult>>()
    val trailers: LiveData<List<TrailerResult>> get() = _trailers

    private val _isTrailerVisible = MutableLiveData<Boolean>()
    val isTrailerVisible: LiveData<Boolean> get() = _isTrailerVisible

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getMovieDetails(title: String): LiveData<Movie?> {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieByTitle(title)
                _movieDetails.postValue(movie)
                movie?.let {
                    _isFavorite.value = repository.isFavorite(account_id, "Bearer $API_KEY", it.id)
                    getTrailer(it.id)
                }
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error fetching movie details: ${e.message}")
            }
        }
        return movie
    }

    fun getTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                val trailers = repository.getTrailers(movieId, "Bearer $API_KEY")
                _trailers.postValue(trailers)
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error fetching trailers: ${e.message}")
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            try {
                val isFavorite = _isFavorite.value ?: false
                if (isFavorite) {
                    repository.removeFavorite(account_id, "Bearer $API_KEY", movie.id)
                } else {
                    repository.addFavorite(account_id, "Bearer $API_KEY", movie)
                }
                _isFavorite.value = !isFavorite
                Log.d("MovieDetailsViewModel", "Favorite status toggled: ${!isFavorite}")
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error toggling favorite status: ${e.message}")
            }
        }
    }

    fun setTrailerVisible(visible: Boolean) {
        _isTrailerVisible.value = visible
    }
}

class MovieDetailsViewModelFactory(private val repository: MovieDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
