package com.dominic.movieswatch.repository

import com.dominic.movieswatch.api.ApiService
import com.dominic.movieswatch.database.MovieDao
import com.dominic.movieswatch.model.Movie


class MovieRepository(private val apiService: ApiService,private val movieDao: MovieDao) {
    suspend fun getUpcoming(apiKey: String) = apiService.getNowPlaying(apiKey)
    suspend fun addFavoriteMovie(movie: Movie) = movieDao.addFavoriteMovie(movie)
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()
    suspend fun removeFavoriteMovie(movie: Movie) = movieDao.removeFavoriteMovie(movie)

    }
