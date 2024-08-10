package com.example.atlysassignment.utils

import com.example.atlysassignment.data.remote.response.MovieResponse
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import com.example.atlysassignment.model.MovieModel

fun MovieResponse.mapToMovieModel() = MovieModel(
    this.id,
    this.title,
    this.overview,
    this.posterPath?.let{ "https://image.tmdb.org/t/p/w500${this.posterPath}" }
)

fun List<MovieResponse>.getMovieModelList() = mutableListOf<MovieModel>().apply {
    this@getMovieModelList.forEach {
        add(it.mapToMovieModel())
    }
}
