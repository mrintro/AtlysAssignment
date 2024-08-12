package com.example.atlysassignment.ui.composeui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.example.atlysassignment.model.MovieModel

@Composable
fun MovieListGrid(
    listItem: List<MovieModel>,
    itemClick: (id: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(listItem.size) {index ->
            MovieContent(
                item = listItem[index],
                onClick = itemClick
            )
        }
    }

}