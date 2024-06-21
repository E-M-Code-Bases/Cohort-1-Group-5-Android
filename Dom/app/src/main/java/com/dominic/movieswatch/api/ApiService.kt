package com.dominic.movieswatch.api

import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Response<MoviesResponse>
    @GET("/movie/NowPlaying")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String, i: Int): Response<List<MoviesResponse>>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, i: Int): Response<List<MoviesResponse>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, i: Int): Response<List<MoviesResponse>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String, i: Int): Response<List<MoviesResponse>>


    @GET("movie/trailers")
    suspend fun getTrailers( @Query("api_key") apiKey: String):Response<List<MoviesResponse>>

    @GET("movie/reviews")
    suspend fun getReviews(@Query("api_key") apiKey: String):Response<List<MoviesResponse>>
}