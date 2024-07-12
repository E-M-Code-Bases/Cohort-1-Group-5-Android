package com.dominic.movieswatch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.NowRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NowPlayingViewModel(private val repo: NowRepo) : ViewModel() {
    var nowPlayingMovies = MutableLiveData<List<Movie>>(emptyList())

    init {
        fetchNowPlayingMovies()
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val movies = repo.getNowPlaying()
                    nowPlayingMovies.postValue(movies)
                    Log.d("NowPlayingViewModel", "Movies fetched from network: ${movies.size}")
                } catch (e: Exception) {
                    Log.e("NowPlayingViewModel", "Error fetching now playing movies", e)
                }
                delay(10000L) // Delay for 10 seconds before fetching again (adjust as needed)
            }
        }
    }
}

class NowPlayingProvider(private val repo: NowRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NowPlayingViewModel(repo) as T
    }
}
