package com.dominic.movieswatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val popularMovies = MutableLiveData<List<MoviesResponse>>()
    val topRatedMovies=MutableLiveData<List<MoviesResponse>>()
    val upcomingMovies=MutableLiveData<List<MoviesResponse>>()
    val nowPlayingMovies=MutableLiveData<List<MoviesResponse>>()
    val trailers=MutableLiveData<List<MoviesResponse>>()

    private val apiKey = "97e4139678874939dde9e3da738d82f1"

    init {
        fetchPopularMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
        fetchNowPlayingMovies()
        fetchTrailers()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularMovies(apiKey)
                if (response.isSuccessful) {
                    popularMovies.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getTopRatedMovies(apiKey)
                if (response.isSuccessful) {
                    topRatedMovies.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getUpcomingMovies(apiKey)
                if (response.isSuccessful) {
                    upcomingMovies.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getNowPlaying(apiKey)
                if (response.isSuccessful) {
                    nowPlayingMovies.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun fetchTrailers() {
        viewModelScope.launch {
            try {
                val response = repository.getTrailers(apiKey)
                if (response.isSuccessful) {
                    trailers.postValue(response.body())
                }
            } catch (e: Exception) {

            }
        }
    }
}
