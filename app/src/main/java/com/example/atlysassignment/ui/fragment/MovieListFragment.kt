package com.example.atlysassignment.ui.fragment

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atlysassignment.R
import com.example.atlysassignment.databinding.FragmentMovieListBinding
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.ui.adapter.MovieListAdapter
import com.example.atlysassignment.ui.utils.textUpdate
import com.example.atlysassignment.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val viewModel by viewModels<MovieListViewModel>()

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FragmentMovieListBinding>(view)?.apply {
            movieList.layoutManager = GridLayoutManager(this.root.context, 2)
            movieList.adapter = movieListAdapter
            movieListAdapter.addItemClickCallback(::navigateToDetailsFragment)
            search.searchField.inputType = InputType.TYPE_NULL
            search.searchField.setOnClickListener {
                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToSearchMovieFragment())
            }
            addObservers()
        }
    }

    private fun navigateToDetailsFragment(movieModel: MovieModel) {
        findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieModel))
    }

    private fun addObservers() {
        viewModel.movieListState.observe(viewLifecycleOwner, ::handleMovieListState)
    }

    fun handleMovieListState(movieListState: MovieListViewModel.MovieListState) {
        when(movieListState) {
            is MovieListViewModel.MovieListState.Success -> {
                movieListAdapter.updateFilmList(movieListState.data)
            }
            is MovieListViewModel.MovieListState.Loading -> {

            }
            is MovieListViewModel.MovieListState.Error -> {

            }
        }

    }
}