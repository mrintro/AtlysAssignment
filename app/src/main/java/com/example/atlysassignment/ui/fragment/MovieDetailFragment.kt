package com.example.atlysassignment.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.atlysassignment.R
import com.example.atlysassignment.databinding.FragmentMovieDetailBinding
import com.example.atlysassignment.model.MovieModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment: Fragment(R.layout.fragment_movie_detail) {

    val safeArgs: MovieDetailFragmentArgs by navArgs<MovieDetailFragmentArgs>()

    @Inject
    lateinit var picasso: Picasso

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FragmentMovieDetailBinding>(view)?.apply {
            updateMovieDetails(safeArgs.movieModel)
        }
    }

    private fun FragmentMovieDetailBinding.updateMovieDetails(movieModel: MovieModel) {
        movieModel.posterUrl?.let {
            picasso
                .load(it)
                .into(imageView)
        }
        titleText.text = movieModel.title
        descriptionText.text = movieModel.overview
        executePendingBindings()

    }

}