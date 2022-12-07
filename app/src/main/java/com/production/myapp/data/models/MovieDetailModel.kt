package com.production.myapp.data.models

import com.google.gson.annotations.SerializedName
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.entities.MovieDetail
import com.production.myapp.domain.entities.Season

data class MovieDetailModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("seasons")
    val seasons: List<SeasonModel>
)

fun MovieDetailModel.toEntity(): MovieDetail {
    return MovieDetail(
        id = id,
        name = name,
        seasons = seasons.map { it.toEntity() }
    )
}