package com.dominic.movieswatch.repository

import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response

class NowRepo(private val apikey: String ) {
    private val appModule = AppModule().getRetrofitInstance(apikey)

    suspend fun getNowPlaying(): Response<MoviesResponse> {
        return appModule.getNowPlaying()
    }

  /*  suspend fun getMovieTrailers(movieId: Int): Response<MoviesResponse> {
        return appModule.getTrailer(movieId)
    }*/
}