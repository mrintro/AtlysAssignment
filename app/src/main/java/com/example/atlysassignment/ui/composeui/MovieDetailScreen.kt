package com.example.atlysassignment.ui.composeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.atlysassignment.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: String?,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movieDetailState by movieDetailViewModel.movieState.observeAsState(MovieDetailViewModel.MovieState.Loading)

    movieId?.let {
        movieDetailViewModel.getMovieFromId(id = it)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HandleState(movieDetailState)
    }

}

@Composable
fun HandleState(movieDetailState: MovieDetailViewModel.MovieState) {
    when(movieDetailState) {
        is MovieDetailViewModel.MovieState.Loading -> {

        }
        is MovieDetailViewModel.MovieState.Success -> {
            Text(text = movieDetailState.data.title)
        }
        is MovieDetailViewModel.MovieState.Error -> {

        }
    }
}
