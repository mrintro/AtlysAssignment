package com.example.atlysassignment.utils

object AppConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAM = "api_key"
    const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDZlYTZkMGJiMWEzOTQxZmQ4NTYwYTA2NWNkYzViMyIsIm5iZiI6MTcyMzIxNDI2My40NjMwMTcsInN1YiI6IjYyMTkzZWU2MzgzZGYyMDAxZDg1ZjdiZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.C349gU-T4QCCZU12TEPH8LgdcbRI9FE_R0FzqPYhmaY"

    fun buildImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
}