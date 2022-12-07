package com.production.myapp.data.models.response.movie

import com.google.gson.annotations.SerializedName
import com.production.myapp.data.models.MovieModel

data class GetMovieListWithTotalPagesResponse (
    @SerializedName("results")
    val results: List<MovieModel>,

    @SerializedName("total_pages")
    val totalPages: Int,
)