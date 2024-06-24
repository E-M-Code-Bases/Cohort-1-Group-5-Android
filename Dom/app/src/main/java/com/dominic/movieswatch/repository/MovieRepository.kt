package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiResponse
import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.database.MovieDao
import com.dominic.movieswatch.model.FavoriteMovie
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.model.Trailer
import retrofit2.Response


class MovieRepository(private val apiService: ApiService,private val movieDao: MovieDao) {
    suspend fun getUpcoming(apiKey: String) = apiService.getNowPlaying(apiKey,1)
//    suspend fun addFavoriteMovie(movie: Movie) = movieDao.addToFavoriteMovie(movie)
   fun getFavoriteMovies() = movieDao.getFavoriteMovies()
//    suspend fun removeFavoriteMovie(movie: Movie) = movieDao.removeFavoriteMovie(movie)


    suspend fun getNowPlaying(apiKey: String): Response<List<MoviesResponse>>  {
        return apiService.getNowPlaying(apiKey,1)
    }

    suspend fun getPopularMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getPopularMovies(apiKey, 1)
    }

    suspend fun getTopRatedMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getTopRatedMovies(apiKey, 1)
    }
    suspend fun searchMovies(apiKey: String, query: String): Response<MoviesResponse>{
        return apiService.searchMovies(apiKey, query)
    }

    suspend fun getUpcomingMovies(apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getUpcomingMovies(apiKey, 1)
    }

    suspend fun getTrailers( apiKey: String): Response<List<MoviesResponse>> {
        return apiService.getTrailers(apiKey)
    }

    suspend fun getMovieDetails(movieId: Int): Movie {
        // Fetch movie details from the network
        return apiService.getMovieDetails(movieId)
    }

    suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.isFavorite(movieId)
    }

    suspend fun toggleFavorite(movieId: Int): Boolean {
        val isCurrentlyFavorite = movieDao.isFavorite(movieId)
        if (isCurrentlyFavorite) {
            movieDao.removeFromFavorites(movieId)
        } else {
            val movieDetails = apiService.getMovieDetails(movieId)
            val favoriteMovie = FavoriteMovie(
                movieId = movieDetails.id,
                title = movieDetails.title,
                posterPath = movieDetails.posterPath
            )
            movieDao.addToFavorites(favoriteMovie)
        }
        return !isCurrentlyFavorite
    }

    suspend fun getMovieTrailer(movieId: Int): Trailer {
        // Fetch movie trailer from the network
        return apiService.getMovieTrailer(movieId)
    }
}
