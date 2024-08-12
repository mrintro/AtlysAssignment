package com.example.atlysassignment.data.remote

import com.example.atlysassignment.data.MovieService
import com.example.atlysassignment.data.remote.response.MovieResponse
import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val service: MovieService
) {
    suspend fun getMovieList(): Result<TrendingMovieResponse> =
        try {
            val res = service.getTrendingMovieList()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }

    suspend fun searchMovieQuery(searchQuery: String): Result<SearchMovieResponse> =
        try {
            Result.success(
                service.searchMoveiQuery(searchQuery)
            )
        } catch (e: Exception) {
            Result.failure(e)
        }

    suspend fun getMovieFromId(id: String): Result<MovieResponse> =
        try {
            Result.success(
                service.getMovieFromId(id)
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
}