package com.example.atlysassignment.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingMovieResponse(
    @Json(name="page")
    val page: String,
    @SerializedName("results")
    @Json(name = "results")
    val results: List<MovieResponse>
)

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?
)
