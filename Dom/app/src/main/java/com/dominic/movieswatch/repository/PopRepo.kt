package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class PopRepo(private val apikey: String) {

    private var appModule = AppModule().getRetrofitInstance(apikey)

    suspend fun getPopularMovies(): Response<MoviesResponse> {
        return appModule.getPopularMovies()
    }

}