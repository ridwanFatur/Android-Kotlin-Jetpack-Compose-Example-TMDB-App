package com.production.myapp.domain.entities

import java.io.Serializable

data class MovieDetail(
    val id: Int,
    val name: String,
    val seasons: List<Season>
) : Serializable