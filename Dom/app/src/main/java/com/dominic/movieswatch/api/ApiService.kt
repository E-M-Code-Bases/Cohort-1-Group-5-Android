package com.dominic.movieswatch.api

import com.dominic.movieswatch.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/movie/NowPlaying")
    suspend fun getNowPlaying(@Query(" 97e4139678874939dde9e3da738d82f1") apiKey: String): ApiResponse<List<MoviesResponse>>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): ApiResponse<List<MoviesResponse>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): ApiResponse<List<MoviesResponse>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): ApiResponse<List<MoviesResponse>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): ApiResponse<List<MoviesResponse>>

    @GET("movie/trailers")
    suspend fun getTrailers(@Query("api_key") apiKey: String):ApiResponse<List<MoviesResponse>>

    @GET("movie/reviews")
    suspend fun getReviews(@Query("api_key") apiKey: String):ApiResponse<List<MoviesResponse>>
}