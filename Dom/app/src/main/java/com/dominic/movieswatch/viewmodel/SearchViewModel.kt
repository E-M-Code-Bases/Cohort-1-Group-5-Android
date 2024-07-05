package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.ViewModel
import com.dominic.movieswatch.repository.MovieDetailsRepo

class SearchViewModel(private val repository: MovieDetailsRepo) : ViewModel() {

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
