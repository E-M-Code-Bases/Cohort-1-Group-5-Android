package com.dominic.movieswatch.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.dominic.movieswatch.model.Movie


@Dao
interface MovieDao {


    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Query("UPDATE movie SET isFavorite = 1 WHERE id = :movieId")
    suspend fun markAsFavorite(movieId: Int)

    @Query("UPDATE movie SET isFavorite = 0 WHERE id = :movieId")
    suspend fun removeFromFavorites(movieId: Int)

    @Query("SELECT * FROM movie WHERE category = 'now_watching'")
    fun getNowWatchingMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: Movie)

    @Delete
    suspend fun removeFavoriteMovie(movie: Movie)
}

