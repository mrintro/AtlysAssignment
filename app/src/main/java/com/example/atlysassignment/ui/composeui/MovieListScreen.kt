package com.example.atlysassignment.ui.composeui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.atlysassignment.viewmodel.MovieListViewModel
import com.example.atlysassignment.viewmodel.SearchMovieViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

@Composable
fun MovieListScreen(navController: NavHostController, movieListViewModel: MovieListViewModel, searchMovieViewModel: SearchMovieViewModel) {
    val state by movieListViewModel.movieListState.observeAsState(MovieListViewModel.MovieListState.Loading)
    val searchMovieList by searchMovieViewModel.movieListViewState.observeAsState(SearchMovieViewModel.MovieListViewState.Loading)
    var isSearchBarActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBox(
            {
                isSearchBarActive = it
            },
            onSearchQuery = {
                searchMovieViewModel.debounceAndSearch(it)
            }
        ) {
            if (isSearchBarActive)
                handleSearchMovieList(state = searchMovieList)
        }
        if(isSearchBarActive.not()) {
            handleMovieList(state, navController)
        }
    }
}

@Composable
fun handleSearchMovieList(state: SearchMovieViewModel.MovieListViewState) {
    when(state) {
        is SearchMovieViewModel.MovieListViewState.Loading -> {

        }
        is SearchMovieViewModel.MovieListViewState.Success -> {
            val items = state.data
            MovieListGrid(items) {}
        }
        is SearchMovieViewModel.MovieListViewState.Error -> {

        }
    }
}

@Composable
fun handleMovieList(movieListState: MovieListViewModel.MovieListState, navController: NavHostController) {
    when(movieListState) {
        is MovieListViewModel.MovieListState.Loading -> {

        }
        is MovieListViewModel.MovieListState.Success -> {
            val items = movieListState.data
            MovieListGrid(items) {
                navController.navigate("MovieDetailScreen/$it")
            }
        }
        is MovieListViewModel.MovieListState.Error -> {

        }
    }
}