package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

/**
 Repository class responsible for fetching popular movies.
 *
 * @param apiKey The API key for accessing the movie service.
 */
class PopRepo(private val apiKey: String) {

    // Initializes Retrofit instance using the provided API key.
    private val appModule = AppModule().getRetrofitInstance(apiKey)

    /**
     * Fetches a list of popular movies from the API.
     *
     * @return A Response object containing MoviesResponse data.
     */
    suspend fun getPopularMovies(): Response<MoviesResponse> {
        return appModule.getPopularMovies()
    }
}
