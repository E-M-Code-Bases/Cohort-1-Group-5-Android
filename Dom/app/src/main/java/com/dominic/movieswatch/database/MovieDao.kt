package com.dominic.movieswatch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dominic.movieswatch.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Delete
    suspend fun removeFavoriteMovie(movie: Movie)
}