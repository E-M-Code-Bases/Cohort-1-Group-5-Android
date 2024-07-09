package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.FavoriteRequest
import com.dominic.movieswatch.model.TrailerResult
import retrofit2.HttpException

class MovieDetailsRepo(private val apikey: String) {
    private val apiService: ApiService = AppModule().getRetrofitInstance(apikey)

    suspend fun getMovieByTitle(title: String): Movie? {
        val response = apiService.getMovieByTitle(title)
        return if (response.isSuccessful) {
            response.body()?.results?.firstOrNull { it.title == title }
        } else {
            null
        }
    }

    suspend fun getTrailers(movieId: Int, authHeader: String): List<TrailerResult> {
        val response = apiService.getTrailers(movieId, authHeader)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            throw HttpException(response)
        }
    }

    suspend fun isFavorite(accountId: String, authHeader: String, movieId: Int): Boolean {
        val response = apiService.getFavoriteMovies(accountId, authHeader)
        if (response.isSuccessful) {
            val favoriteMovies = response.body()?.results ?: emptyList()
            return favoriteMovies.any { it.id == movieId }
        }
        return false
    }

    suspend fun addFavorite(accountId: String, authHeader: String, movie: Movie) {
        val request = FavoriteRequest(mediaType = "movie", mediaId = movie.id, favorite = true)
        val response = apiService.markAsFavorite(accountId, authHeader, request)
        if (!response.isSuccessful) throw HttpException(response)
    }

    suspend fun removeFavorite(accountId: String, authHeader: String, movieId: Int) {
        val request = FavoriteRequest(mediaType = "movie", mediaId = movieId, favorite = false)
        val response = apiService.markAsFavorite(accountId, authHeader, request)
        if (!response.isSuccessful) throw HttpException(response)
    }
}
