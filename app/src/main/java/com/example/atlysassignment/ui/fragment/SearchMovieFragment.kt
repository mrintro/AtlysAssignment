package com.example.atlysassignment.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.atlysassignment.R
import com.example.atlysassignment.databinding.FragmentSearchMovieBinding
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.ui.adapter.MovieListAdapter
import com.example.atlysassignment.ui.utils.textUpdate
import com.example.atlysassignment.viewmodel.SearchMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SearchMovieFragment : Fragment(R.layout.fragment_search_movie) {
    private val viewModel by viewModels<SearchMovieViewModel>()

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    private var binding: FragmentSearchMovieBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind<FragmentSearchMovieBinding>(view)?.apply {
            search.searchField.requestFocus()
            addTextWatcher(search.searchField)
            movieList.adapter = movieListAdapter
            movieListAdapter.addItemClickCallback(::navigateToMovieDetail)
            addObservers()
            movieList.layoutManager = GridLayoutManager(this.root.context, 2)
        }
    }

    private fun navigateToMovieDetail(item: MovieModel) {
        findNavController().navigate(
            SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailFragment(
                item
            )
        )
    }

    private fun addObservers() {
        viewModel.movieListViewState.observe(viewLifecycleOwner) {
            when (it) {
                is SearchMovieViewModel.MovieListViewState.Success -> {
                    movieListAdapter.updateFilmList(it.data)
                    binding?.loader?.visibility = View.GONE
                    binding?.movieList?.visibility = View.VISIBLE
                }

                is SearchMovieViewModel.MovieListViewState.Loading -> {
                    binding?.loader?.visibility = View.VISIBLE
                    binding?.movieList?.visibility = View.INVISIBLE
                }

                is SearchMovieViewModel.MovieListViewState.Error -> {
                    binding?.loader?.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_message),
                        Toast.LENGTH_LONG
                    ).show()
                    binding?.movieList?.visibility = View.INVISIBLE
                }
            }
        }
    }


    @OptIn(FlowPreview::class)
    private fun addTextWatcher(searchField: EditText) {
        searchField.textUpdate()
            .map { it.toString() }
            .debounce(500)
            .filterNot { it.isBlank() }
            .distinctUntilChanged()
            .onEach {
                viewModel.searchMovie(it)
            }
            .launchIn(lifecycleScope)
    }


}