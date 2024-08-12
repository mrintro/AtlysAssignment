package com.example.atlysassignment.data

import com.example.atlysassignment.data.remote.RemoteDataSource
import com.example.atlysassignment.data.remote.response.MovieResponse
import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import com.example.atlysassignment.domain.MovieRepository
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.utils.mapToMovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
    override fun getMovieList(): Flow<Result<TrendingMovieResponse>> = flow {
        emit(remoteDataSource.getMovieList())
    }

    override fun searchMovie(searchQuery: String): Flow<Result<SearchMovieResponse>> = flow {
        emit(remoteDataSource.searchMovieQuery(searchQuery))
    }

    override fun getMovieFromId(id: String): Flow<Result<MovieResponse>> = flow {
        emit(remoteDataSource.getMovieFromId(id))
    }
}