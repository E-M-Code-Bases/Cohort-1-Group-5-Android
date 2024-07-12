package com.dominic.movieswatch.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.dominic.movieswatch.model.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}

