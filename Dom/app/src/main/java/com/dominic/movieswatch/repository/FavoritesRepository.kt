package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.utils.API_KEY
import retrofit2.Response

class FavoritesRepository (private val apiKey : String){
    private val apiService: ApiService = AppModule().getRetrofitInstance(apiKey)


    suspend fun getFavoriteMovies(accountId: String, authHeader: String): Response<MoviesResponse> {
        return apiService.getFavoriteMovies(accountId, authHeader)
    }
}