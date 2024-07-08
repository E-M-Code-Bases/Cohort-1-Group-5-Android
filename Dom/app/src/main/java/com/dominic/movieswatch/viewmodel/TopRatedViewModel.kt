import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.TopRatedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TopRatedViewModel(private val repository: TopRatedRepository) : ViewModel() {

    var topRatedMovies = MutableLiveData<List<Movie>>(emptyList())

    init {
        fetchTopRatedMovies()

    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repository.getTopRated()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            topRatedMovies.postValue(response.body()?.results)
                        } else {
                            Log.d("", "no new movies found")
                        }
                    } else {
                        Log.d("", "Error fetching Top Rated movies: ${response.code()}")
                    }

                } catch (e: Exception) {
                    Log.e("", "Error fetching TopRatedMovies", e)
                }
                delay(10000L)
            }

        }
    }
}

class TRProvider(private val repository: TopRatedRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopRatedViewModel(repository) as T
    }


}
