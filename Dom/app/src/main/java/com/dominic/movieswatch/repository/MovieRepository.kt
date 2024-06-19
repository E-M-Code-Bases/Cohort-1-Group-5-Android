package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService


class MovieRepository(private val apiService: ApiService) {
        suspend fun getUpcoming(apiKey: String) = apiService.getNowPlaying(apiKey)

    }
