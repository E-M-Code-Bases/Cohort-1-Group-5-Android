package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class UpcomingRepo(private val api_key: String) {
    private val appModule = AppModule().getRetrofitInstance(api_key)

    suspend fun getUpcomingMovies():Response<MoviesResponse> {
            return appModule.getUpcomingMovies()
        }
    }

