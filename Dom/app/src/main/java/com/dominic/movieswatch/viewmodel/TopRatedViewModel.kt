import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.repository.MovieRepository
import kotlinx.coroutines.launch

class TopRatedViewModel(private val repository: MovieRepository) : ViewModel() {


    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> get() = _topRatedMovies
    }

//    fun fetchTopRatedMovies(apiKey: String) {
//        viewModelScope.launch {
//            try {
//                val response = repository.getTopRatedMovies(apiKey)
//



////                _topRatedMovies.value = response.results
//            } catch (e: Exception) {
//                // Handle the error
//            }
//        }
//    }
//}
//
//class TopRatedViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TopRatedViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TopRatedViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
