package com.dominic.movieswatch.api

import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.model.FavoriteRequest
import com.dominic.movieswatch.model.VideoResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("search/movie")
    suspend fun getMovieByTitle(@Query("query") title: String): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") i: Int = 1): Response<MoviesResponse>

    @GET("movie/{movie_id}/videos")
       suspend fun getMovieVideos(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Response<VideoResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") i: Int = 1): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") i: Int = 1): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") i: Int = 1): Response<MoviesResponse>

    @GET("movie/trailers")
    suspend fun getTrailers(@Query("page") i: Int = 1): Response<MoviesResponse>

    @POST("account/{account_id}/favorite")
    suspend fun markAsFavorite(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
        @Body favoriteRequest: FavoriteRequest
    ): Response<Unit>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
//        @Query("language") language: String = "en-US",
//        @Query("sort_by") sortBy: String = "created_at.asc",
//        @Query("page") page: Int = 1
    ): Response<MoviesResponse>

    @GET("account/{account_id}/favorite/movies")
    suspend fun isFavorite(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
        @Query("movie_id") movieId: Int
    ): Response<MoviesResponse>
}
