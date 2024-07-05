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
    var errorMessage = MutableLiveData<String>()


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
                           errorMessage.postValue("No new movies found")
                       }
                   } else {
                       errorMessage.postValue("Error fetching Now Playing movies: ${response.code()}")
                   }


               } catch (e: Exception) {
                   errorMessage.postValue("Error fetching upcoming movies: ${e.message}")
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