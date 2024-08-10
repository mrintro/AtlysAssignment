package com.example.atlysassignment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: String,
    val title: String,
    val overview: String,
    val posterUrl: String?,
): Parcelable
