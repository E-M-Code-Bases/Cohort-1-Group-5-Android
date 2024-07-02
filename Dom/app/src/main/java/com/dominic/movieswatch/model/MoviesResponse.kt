package com.dominic.movieswatch.model


data class MoviesResponse(
    val page: Int,
    val results: List<Movie>
)