package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
            val response = repository.getFavoriteMovies(accountId, authHeader)
            if (response.isSuccessful) {
                _favoriteMovies.postValue(response.body()?.results)
            }
        }
    }
}