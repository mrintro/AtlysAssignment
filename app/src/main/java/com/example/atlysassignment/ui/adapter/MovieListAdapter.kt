package com.example.atlysassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.atlysassignment.R
import com.example.atlysassignment.databinding.MovieListItemBinding
import com.example.atlysassignment.model.MovieModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import javax.inject.Inject

class MovieListAdapter @Inject constructor(
    private val picasso: Picasso
): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private var items: ArrayList<MovieModel> = ArrayList<MovieModel>()
    private var itemClickCallback: ((movieModel: MovieModel) -> Unit)? = null

    fun addItemClickCallback(callback: (movieModel: MovieModel) -> Unit) {
        itemClickCallback = callback
    }

    class MovieListViewHolder(val binding: MovieListItemBinding): ViewHolder(binding.root) {
        fun bind(item: MovieModel, picasso: Picasso) {
            binding.movieTitle.text = item.title
            binding.movieImage.setImageDrawable(null)
            item.posterUrl?.let {
                picasso
                    .load(it)
                    .into(binding.movieImage)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size ?: 0

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        items.getOrNull(position)?.let {movieModel ->
            holder.bind(movieModel, picasso)
            holder.binding.parentLayout.setOnClickListener {
                itemClickCallback?.invoke(movieModel)
            }
        }
    }

    fun updateFilmList(movieList: List<MovieModel>) {
        val diffCallback = MovieListDiffUtil(this.items, movieList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(movieList)
        diffResult.dispatchUpdatesTo(this)
    }

}

class MovieListDiffUtil(val oldList: List<MovieModel>, val newList: List<MovieModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title
}