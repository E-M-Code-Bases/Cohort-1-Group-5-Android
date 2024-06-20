package com.example.movieapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.dominic.movieswatch.model.Movie


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieList(movies: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie WHERE id = :movieId LIMIT 1")
    fun getMovieById(movieId: Int): LiveData<Movie>

    @Query("SELECT * FROM movie WHERE category = :category")
    fun getMoviesByCategory(category: String): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Query("UPDATE movie SET isFavorite = 1 WHERE id = :movieId")
    suspend fun markAsFavorite(movieId: Int)

    @Query("UPDATE movie SET isFavorite = 0 WHERE id = :movieId")
    suspend fun removeFromFavorites(movieId: Int)

    @Query("SELECT * FROM movie WHERE category = 'now_watching'")
    fun getNowWatchingMovies(): LiveData<List<Movie>>
}
