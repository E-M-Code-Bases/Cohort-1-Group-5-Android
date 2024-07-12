
package com.dominic.movieswatch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.PopRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PopularViewmodel(private val repo: PopRepo) : ViewModel() {
    var popularMovies = MutableLiveData<List<Movie>>(emptyList())

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getPopularMovies()
                    popularMovies.postValue(response)
                    Log.d("PopularViewModel", "Movies fetched from network: ${response.size}")
                } catch (e: Exception) {
                    Log.e("PopularViewModel", "Error fetching popular movies", e)
                }
                delay(10000L)

            }
        }
    }
}
class PopularProvider(private val popRepo: PopRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularViewmodel(popRepo) as T
    }

}