package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiResponse
import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) {

    suspend fun getNowPlaying(apiKey: String): Response<List<MoviesResponse>>  {
        return apiService.getNowPlaying(apiKey,1)
    }

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
