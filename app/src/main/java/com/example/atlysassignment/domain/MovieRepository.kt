package com.example.atlysassignment.domain

import com.example.atlysassignment.data.remote.response.MovieResponse
import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<Result<TrendingMovieResponse>>
    fun searchMovie(searchQuery: String): Flow<Result<SearchMovieResponse>>
    fun getMovieFromId(id: String): Flow<Result<MovieResponse>>
}