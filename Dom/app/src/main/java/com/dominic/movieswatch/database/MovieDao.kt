package com.dominic.movieswatch.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.dominic.movieswatch.model.FavoriteMovie
import com.dominic.movieswatch.model.Movie


@Dao
interface MovieDao {


    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Query("UPDATE movie SET isFavorite = 1 WHERE id = :movieId")
    suspend fun markAsFavorite(movieId: Int)

    @Query("DELETE FROM favorite_movies WHERE movieId = :movieId")
    suspend fun removeFromFavorites(movieId: Int)

    @Query("SELECT * FROM movie WHERE category = 'now_watching'")
    fun getNowWatchingMovies(): LiveData<List<Movie>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE movieId = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(movie: FavoriteMovie)

}

