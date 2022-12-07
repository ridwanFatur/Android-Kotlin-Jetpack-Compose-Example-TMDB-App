package com.production.myapp.data.models

import com.google.gson.annotations.SerializedName
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.entities.Season

data class SeasonModel (
    @SerializedName("air_date")
    val airDate: String,

    @SerializedName("overview")
    val overview: String,
)

fun SeasonModel.toEntity(): Season {
    return Season(
        airDate = airDate,
        overview = overview,
    )
}