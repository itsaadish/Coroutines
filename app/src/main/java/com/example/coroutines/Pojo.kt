package com.example.coroutines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class PopularMoviesResponse (

    val page: Int,

    val results: List<Movie>

)

@Parcelize
data class Movie(

    val adult: Boolean = false,

    val backdrop_path: String = "",

    val id: Int = 0,

    val original_language: String = "",

    val original_title: String = "",

    val overview: String = "",

    val popularity: Float = 0f,

    val poster_path: String = "",

    val release_date: String = "",

    val title: String = "",

    val video: Boolean = false,

    val vote_average: Float = 0f,

    val vote_count: Int = 0

) : Parcelable