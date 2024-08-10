package com.example.atlysassignment.data

import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("trending/movie/day")
    suspend fun getTrendingMovieList(): TrendingMovieResponse

    @GET("search/movie")
    suspend fun searchMoveiQuery(@Query("query") searchQuery: String): SearchMovieResponse

}