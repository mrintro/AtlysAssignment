package com.example.atlysassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysassignment.data.remote.response.SearchMovieResponse
import com.example.atlysassignment.domain.MovieRepository
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.utils.getMovieModelList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _movieListViewState = MutableLiveData<MovieListViewState>()
    val movieListViewState: LiveData<MovieListViewState> = _movieListViewState

    fun searchMovie(searchQuery: String) {
        viewModelScope.launch {
            _movieListViewState.value = MovieListViewState.Loading
            withContext(Dispatchers.IO) {
                repository.searchMovie(searchQuery)
                    .map {
                        getMovieStateFromResponse(it)
                    }
                    .collect{
                        _movieListViewState.postValue(it)
                    }
            }
        }
    }

    private fun getMovieStateFromResponse(movieResult: Result<SearchMovieResponse>): MovieListViewState {
        return movieResult.fold({
            MovieListViewState.Success(it.results.getMovieModelList())
        }, {
            MovieListViewState.Error
        })
    }


    sealed interface MovieListViewState {
        data class Success(val data: List<MovieModel>): MovieListViewState
        data object Loading: MovieListViewState
        data object Error: MovieListViewState
    }

}