package com.example.atlysassignment.utils

object AppConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAM = "api_key"
    const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDZlYTZkMGJiMWEzOTQxZmQ4NTYwYTA2NWNkYzViMyIsIm5iZiI6MTcyMzQ2MjMxMi4wNTY4Nywic3ViIjoiNjIxOTNlZTYzODNkZjIwMDFkODVmN2JmIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.HrTqFzX-GqHGrOlsAo3YqWqnsh6mJUMlY-eCv0HcC6k"
    fun buildImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
}