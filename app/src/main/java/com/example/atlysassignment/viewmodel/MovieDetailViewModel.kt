package com.example.atlysassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysassignment.data.remote.response.MovieResponse
import com.example.atlysassignment.domain.MovieRepository
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.utils.getMovieModelList
import com.example.atlysassignment.utils.mapToMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _movieState = MutableLiveData<MovieState>(MovieState.Loading)
    val movieState: LiveData<MovieState> = _movieState
    
    fun getMovieFromId(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getMovieFromId(id)
                    .map { getMovieStateFromResponse(it) }
                    .collect {
                        _movieState.postValue(it)
                    }
            }
        }
    }

    private fun getMovieStateFromResponse(movieResult: Result<MovieResponse>): MovieState {
        return movieResult.fold({
            MovieState.Success(it.mapToMovieModel())
        }, {
            MovieState.Error
        })
    }

    sealed interface MovieState {
        data class Success(val data: MovieModel) : MovieState
        data object Loading : MovieState
        data object Error : MovieState
    }
}