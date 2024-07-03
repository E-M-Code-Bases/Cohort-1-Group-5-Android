package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.*
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.MovieRepository
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.account_id
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<Movie?>()
    val movie: MutableLiveData<Movie?> get() = _movieDetails

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getMovieDetails(title: String): MutableLiveData<Movie?> {
        viewModelScope.launch {
            val movie = repository.getMovieByTitle(title)
            _movieDetails.postValue(movie)
            _isFavorite.postValue(repository.isFavorite(account_id, API_KEY, movie?.id ?: -1))
        }
        return movie
    }

    fun toggleFavoriteStatus(movie: Movie) {
        viewModelScope.launch {
            if (_isFavorite.value == true) {
                repository.removeFavorite(account_id, API_KEY, movie.id)
            } else {
                repository.addFavorite(account_id, API_KEY, movie)
            }
            _isFavorite.postValue(!_isFavorite.value!!)
        }
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
