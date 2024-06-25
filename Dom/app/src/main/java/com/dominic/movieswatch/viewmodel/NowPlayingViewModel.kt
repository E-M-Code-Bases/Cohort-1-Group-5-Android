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
                    val response = repo.getNowPlaying()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            nowPlayingMovies.postValue(response.body()?.results)
                        } else {
                            Log.d("", "no new  movies found")

                        }
                    } else {
                        Log.d("", "Error fetching Now Playing movies: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("", "Error fetching nowPlaying movies", e)
                }
                delay(10000L)
            }
        }


    }
}


class NowPlayingProvider(private val repo: NowRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NowPlayingViewModel(repo) as T
    }
}
