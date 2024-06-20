package com.dominic.movieswatch.viewmodel

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel (private val repository: MovieRepository): ViewModel (){

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies
    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> get() = _topRatedMovies
    private val _upcomingMovies = MutableLiveData<Movie>()
    val upcomingMovies: LiveData<Movie> get() = _upcomingMovies
    private val _nowPlayingMovies = MutableLiveData<Movie>()
    val nowPlayingMovies: LiveData<Movie> get() = _nowPlayingMovies
    private val _trailers = MutableLiveData<List<Movie>>()
    val trailers: LiveData<List<Movie>> get() = _trailers

    init {
    fetchPopularMovies()
    fetchTopRatedMovies()
    fetchUpcomingMovies()
    fetchNowPlayingMovies()
    fetchTrailers()
    }
    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val movies = repository.getPopularMovies(apiKey = String.toString())
            _popularMovies.postValue(movies)
        }
    }
    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            val movies = repository.getTopRatedMovies(apiKey = String.toString())
            _topRatedMovies.postValue(movies)
        }
    }
    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            val movies = repository.getUpcomingMovies(apiKey = String.toString())
            _upcomingMovies.postValue(movies)
        }


    }
    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            val movies = repository.getNowPlaying(apiKey = String.toString())
            _nowPlayingMovies.postValue(movies)
        }

        }
    private fun fetchTrailers() {
        viewModelScope.launch {
            val movies = repository.getTrailers(apiKey = String.toString())
            _trailers.postValue(movies)
        }
    }
}