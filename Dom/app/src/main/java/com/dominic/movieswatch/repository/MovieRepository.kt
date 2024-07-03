package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.FavoriteRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val apiKey: String) {

    private val apiService: ApiService = AppModule().getRetrofitInstance(apiKey)

    suspend fun getMovieByTitle(title: String): Movie? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getMovieByTitle(title)
            if (response.isSuccessful) {
                response.body()?.results?.firstOrNull { it.title == title }
            } else {
                null
            }
        }
    }

    suspend fun isFavorite(accountId: String, authHeader: String, movieId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = apiService.isFavorite(accountId, authHeader, movieId)
            response.isSuccessful && response.body()?.results?.any { it.id == movieId } == true
        }
    }

    suspend fun addFavorite(accountId: String, authHeader: String, movie: Movie) {
        withContext(Dispatchers.IO) {
            apiService.markAsFavorite(
                accountId = accountId,
                authHeader = authHeader,
                favoriteRequest = FavoriteRequest("movie", movie.id, true)
            )
        }
    }

    suspend fun removeFavorite(accountId: String, authHeader: String, movieId: Int) {
        withContext(Dispatchers.IO) {
            apiService.markAsFavorite(
                accountId = accountId,
                authHeader = authHeader,
                favoriteRequest = FavoriteRequest("movie", movieId, false)
            )
        }
    }

    suspend fun getFavoriteMovies(accountId: String, authHeader: String): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getFavoriteMovies(accountId, authHeader)
            if (response.isSuccessful) {
                response.body()?.results
            } else {
                null
            }
        }
    }
}
