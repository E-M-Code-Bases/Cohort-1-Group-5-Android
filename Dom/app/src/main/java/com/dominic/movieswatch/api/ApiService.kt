package com.dominic.movieswatch.api

import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") i: Int=1): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")i: Int=1): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") i: Int=1): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") i: Int=1): Response<MoviesResponse>


    @GET("movie/trailers")
    suspend fun getTrailers(@Query("page") i: Int=1): Response<MoviesResponse>

}