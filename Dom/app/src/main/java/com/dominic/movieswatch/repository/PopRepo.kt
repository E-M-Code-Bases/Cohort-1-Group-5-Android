package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class PopRepo(private val apiKey: String) {

    // Initializes Retrofit instance using the provided API key.
    private val appModule = AppModule().getRetrofitInstance(apiKey)
   // A Response containing the MoviesResponse data.
    suspend fun getPopularMovies(): Response<MoviesResponse> {
        return appModule.getPopularMovies()

    }
}
