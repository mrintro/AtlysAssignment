package com.example.atlysassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysassignment.data.remote.response.TrendingMovieResponse
import com.example.atlysassignment.domain.MovieRepository
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.utils.getMovieModelList
import com.example.atlysassignment.utils.mapToMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    private val _movieListState = MutableLiveData<MovieListState>(MovieListState.Loading)
    val movieListState: LiveData<MovieListState> = _movieListState

    init {
        fetchMovieList()
    }

    private fun fetchMovieList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository
                    .getMovieList()
                    .onStart { MovieListState.Loading }
                    .map {
                        getMovieStateFromResponse(it)
                    }
                    .collect {
                        _movieListState.postValue(it)
                    }
            }
        }
    }

    private fun getMovieStateFromResponse(movieResult: Result<TrendingMovieResponse>): MovieListState {
        return movieResult.fold({
            MovieListState.Success(it.results.getMovieModelList())
        }, {
            MovieListState.Error
        })
    }

    sealed interface MovieListState {
        data class Success(val data: List<MovieModel>) : MovieListState
        data object Loading : MovieListState
        data object Error : MovieListState
    }

}