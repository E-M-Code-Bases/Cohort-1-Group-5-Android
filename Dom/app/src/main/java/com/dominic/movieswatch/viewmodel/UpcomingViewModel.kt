package com.dominic.movieswatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.UpcomingRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class UpcomingViewModel(val repository: UpcomingRepo):ViewModel (){
     var upcomingMovies = MutableLiveData<List<Movie>>(emptyList())


    init {
        fetchUpcomingMovies()
    }
    private fun fetchUpcomingMovies(){
       viewModelScope.launch {
           while (isActive) {
               try {
                   val response = repository.getUpcomingMovies()
                   if (response.isSuccessful) {
                       if (response.body()!!.results.isNotEmpty()) {
                           upcomingMovies.postValue(response.body()?.results)
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
class UpcomingMoviesProvider(private val repository: UpcomingRepo ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UpcomingViewModel(repository) as T
    }
}