package com.dominic.movieswatch.api

import androidx.room.Entity

class ApiResponse <T> (
    val data: T,
    val message: String,
    val statusCode: Int
)