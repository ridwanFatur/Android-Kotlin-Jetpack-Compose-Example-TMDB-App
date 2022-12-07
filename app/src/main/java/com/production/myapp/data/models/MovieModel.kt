package com.production.myapp.data.models

import com.google.gson.annotations.SerializedName
import com.production.myapp.domain.entities.Movie


data class MovieModel(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int,
)

fun MovieModel.toEntity(): Movie {
    return Movie(
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        id = id,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}