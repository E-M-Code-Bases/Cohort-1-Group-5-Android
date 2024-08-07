package com.dominic.movieswatch.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "genre_ids") val genreIds: List<Int>?,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis()
) : Parcelable {
    override fun hashCode(): Int {
        return id
    }
}

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

fun String?.orDefault(default: String): String {
    return this ?: default
}

fun List<Int>?.orEmptyList(): List<Int> {
    return this ?: emptyList()
}