package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService


class MovieRepository(private val apiService: ApiService) {
        suspend fun getNowPlaying(apiKey: String) = apiService.getNowPlaying(apiKey)

        suspend fun getPopularMovies(apiKey: String) = apiService.getPopularMovies(apiKey)

        suspend fun getTopRatedMovies(apiKey: String) = apiService.getTopRatedMovies(apiKey)

        suspend fun getUpcomingMovies(apiKey: String) = apiService.getUpcomingMovies(apiKey)

        suspend fun getTrailers(apiKey: String) = apiService.getTrailers(apiKey)
    }
