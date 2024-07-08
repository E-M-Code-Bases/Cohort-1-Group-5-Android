package com.dominic.movieswatch.repository

import android.util.Log
import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class PopRepo(private val apiKey: String) {

    // Initializes Retrofit instance using the provided API key.
    private val appModule = AppModule().getRetrofitInstance(apiKey)

    suspend fun getPopularMovies(): Response<MoviesResponse> {
        Log.d("PopRepo", "Fetching popular movies")
        return appModule.getPopularMovies().also {
            Log.d("PopRepo", "Fetched ${it.body()?.results?.size ?: 0} movies")
        }
    }
}
