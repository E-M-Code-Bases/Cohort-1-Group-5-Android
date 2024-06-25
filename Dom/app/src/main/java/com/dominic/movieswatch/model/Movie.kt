package com.dominic.movieswatch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int, // Assuming 'id' is the primary key
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "genre_ids") val genreIds: List<Int>, // Room does not support Lists directly
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int
) : Serializable // Serializable is used if you need to pass objects between activities/fragments

class Converters {
    @TypeConverter
    fun fromGenreIdsList(genreIds: List<Int>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(genreIds, type)
    }

    @TypeConverter
    fun toGenreIdsList(genreIdsString: String): List<Int> {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(genreIdsString, type)
    }
}