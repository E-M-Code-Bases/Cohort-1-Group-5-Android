package com.dominic.movieswatch.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey val movieId: Int,
    val title: String,
    val posterPath: String
)