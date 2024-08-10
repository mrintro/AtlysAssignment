package com.example.atlysassignment.utils

object AppConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAM = "api_key"

    fun buildImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
}