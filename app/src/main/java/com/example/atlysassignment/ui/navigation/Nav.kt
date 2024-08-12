package com.example.atlysassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.atlysassignment.ui.composeui.MovieDetailScreen
import com.example.atlysassignment.ui.composeui.MovieListScreen
import com.example.atlysassignment.viewmodel.MovieListViewModel
import com.example.atlysassignment.viewmodel.SearchMovieViewModel

@Composable
fun Nav(
    movieListViewModel: MovieListViewModel,
    searchMovieViewModel: SearchMovieViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MovieListScreen") {
        composable( route = "MovieListScreen" ) {
            MovieListScreen(navController, movieListViewModel, searchMovieViewModel)
        }
        composable(
            route = "MovieDetailScreen/{movieId}"
        ) {
            val movieId = it.arguments?.getString("movieId")
            MovieDetailScreen(navController, movieId)
        }
    }
}