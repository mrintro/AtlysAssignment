package com.example.atlysassignment.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.xml.transform.Result

@Json
@JsonClass(generateAdapter = true)
data class SearchMovieResponse(
    val results: List<MovieResponse>
)
