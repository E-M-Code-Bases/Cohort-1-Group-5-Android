package com.dominic.movieswatch.api

import com.dominic.movieswatch.ui.fragments.NowPlaying
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/movie/NowPlaying")
    suspend fun getNowPlaying(@Query(" 97e4139678874939dde9e3da738d82f1") apiKey: String): NowPlaying
}