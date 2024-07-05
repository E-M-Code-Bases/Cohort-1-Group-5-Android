package com.dominic.movieswatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.FavoritesRepository
import com.dominic.movieswatch.repository.MovieDetailsRepo
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoritesRepository) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteMovies: LiveData<List<Movie>> get() = _favoriteMovies

    fun fetchFavoriteMovies(accountId: String, authHeader: String) {
        viewModelScope.launch {
            try {
                val response = repository.getFavoriteMovies(accountId, authHeader)
                if (response.isSuccessful) {
                    _favoriteMovies.postValue(response.body()?.results)
                } else {
                    Log.e("FavoriteViewModel", "Failed to fetch favorite movies: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error fetching favorite movies", e)
            }
        }
    }
}

class FavoriteMoviesViewModelFactory(private val movieRepository: FavoritesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}