package com.production.myapp.domain.entities

import java.io.Serializable

data class Season(
    val airDate: String,
    val overview: String,
) : Serializable