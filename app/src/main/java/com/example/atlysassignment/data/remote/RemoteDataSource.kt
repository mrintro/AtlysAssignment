package com.example.atlysassignment.data.remote

import com.example.atlysassignment.data.MovieService
import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
}