package com.dominic.movieswatch.repository

import android.content.Context
import android.util.Log
import com.dominic.movieswatch.database.AppDatabase
import com.dominic.movieswatch.di.AppModule
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.model.MoviesResponse
import com.dominic.movieswatch.model.orDefault
import com.dominic.movieswatch.model.orEmptyList
import retrofit2.Response

class PopRepo(private val apiKey: String, private val context: Context) {
    private val appModule = AppModule().getRetrofitInstance(apiKey)
    private val movieDao = AppDatabase.getDatabase(context).movieDao()

    suspend fun getPopularMovies(): List<Movie> {
        val freshnessThreshold = System.currentTimeMillis() - FRESHNESS_INTERVAL
        val cachedMovies = movieDao.getFreshMovies(freshnessThreshold)

        return if (cachedMovies.isNotEmpty()) {
            Log.d("PopRepo", "Cached movies ngapi: ${cachedMovies.size}")
            cachedMovies
        } else {
            val response = appModule.getPopularMovies()
            if (response.isSuccessful) {
                response.body()?.let { moviesResponse ->
                    val movies = moviesResponse.results.map {
                        Movie(
                            id = it.id,
                            adult = it.adult,
                            backdropPath = it.backdropPath.orDefault("default_backdrop_path"),
                            genreIds = it.genreIds.orEmptyList(),
                            originalLanguage = it.originalLanguage.orDefault("Unknown"),
                            originalTitle = it.originalTitle.orDefault("Unknown"),
                            overview = it.overview.orDefault("No overview available"),
                            popularity = it.popularity ?: 0.0,
                            posterPath = it.posterPath.orDefault("default_poster_path"),
                            releaseDate = it.releaseDate.orDefault("Unknown"),
                            title = it.title.orDefault("Untitled"),
                            video = it.video,
                            voteAverage = it.voteAverage ?: 0.0,
                            voteCount = it.voteCount ?: 0,
                            timestamp = System.currentTimeMillis()
                        )
                    }

                    insertMovies(movies)
                    Log.d("PopRepo", "Movies fetched from network: ${movies.size}")
                    return movies
                }
            } else {
                Log.e("PopRepo", "Failed to fetch popular movies: ${response.errorBody()?.string()}")
            }
            emptyList()
        }
    }

    private suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
        Log.d("PopRepo", "Movies inserted into database: ${movies.size}")
    }

    companion object {
        const val FRESHNESS_INTERVAL = 1000 * 60 * 60 * 24
    }
}