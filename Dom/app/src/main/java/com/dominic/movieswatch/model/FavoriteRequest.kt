package com.dominic.movieswatch.model

data class FavoriteRequest(
    val movie_type: String,
    val movie_id: Int,
    val favorite: Boolean
)
