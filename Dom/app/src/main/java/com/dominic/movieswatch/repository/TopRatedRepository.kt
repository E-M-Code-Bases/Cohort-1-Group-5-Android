package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class TopRatedRepository(private val apikey: String ) {

    private val appModule = AppModule().getRetrofitInstance(apikey)

    suspend fun getTopRated(): Response<MoviesResponse> {
        return appModule.fetchTopRatedMovies()
    }
}

