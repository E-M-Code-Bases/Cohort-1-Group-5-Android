package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.Movie

class MovieRepository(private val apikey: String ) {
    private val appModule = AppModule().getRetrofitInstance(apikey)
    suspend fun getMovieByTitle(title: String): Movie? {
        val response = appModule.getMovieByTitle(title)
        return if (response.isSuccessful) {
            response.body()?.results?.firstOrNull { it.title == title }
        } else {
            null
        }
    }
}


/*class MovieRepository(private val apiService: ApiService,private val movieDao: MovieDao) {

    //suspend fun getUpcoming(apiKey: String) = apiService.getNowPlaying(apiKey,1)
    suspend fun addFavoriteMovie(movie: Movie) = movieDao.addFavoriteMovie(movie)
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()
    suspend fun removeFavoriteMovie(movie: Movie) = movieDao.removeFavoriteMovie(movie)



    suspend fun getPopularMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getPopularMovies(apiKey, 1)
    }

    suspend fun getTopRatedMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getTopRatedMovies(apiKey, 1)
    }

    suspend fun getUpcomingMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getUpcomingMovies(apiKey, 1)
    }

    suspend fun getTrailers(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getTrailers(apiKey, 1)
    }
}
*/