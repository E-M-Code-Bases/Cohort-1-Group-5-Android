package com.dominic.movieswatch.viewmodel
/*
import android.util.Log
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
    val trailers=MutableLiveData<List<MoviesResponse>>()}


    init {
        fetchPopularMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
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
                Log.e("", "Error fetching popular movies", e)
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
                Log.e("", "Error fetching top rated movies ", e)
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
                Log.e("", "Error fetching upcoming movies", e)
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
            } catch (e: Error) {
                Log.e("", "Error fetching trailers", e)

            }
        }
    }
}*/
