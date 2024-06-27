package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: MovieRepository) : ViewModel() {

    /*private val _searchResults = MutableLiveData<List<Movie>>()
    val searchResults: LiveData<List<Movie>> get() = _searchResults

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchMovies(apiKey: String, query: String) = viewModelScope.launch {
        try {
            val response = repository.searchMovies(apiKey, query)
            if (response.isSuccessful && response.body() != null) {
                _searchResults.postValue(response.body()?.results)
            } else {
                _error.postValue("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            _error.postValue("Exception: ${e.message}")
        }
    }*/
}
