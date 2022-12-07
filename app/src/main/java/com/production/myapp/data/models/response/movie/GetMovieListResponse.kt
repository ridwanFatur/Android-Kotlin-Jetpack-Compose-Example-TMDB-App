package com.production.myapp.data.models.response.movie

import com.google.gson.annotations.SerializedName
import com.production.myapp.data.models.MovieModel

data class GetMovieListResponse (
    @SerializedName("results")
    val results: List<MovieModel>,
)