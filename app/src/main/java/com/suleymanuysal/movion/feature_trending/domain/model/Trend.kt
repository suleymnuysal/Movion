package com.suleymanuysal.movion.feature_trending.domain.model

data class Trend(
    val id: Int,
    val mediaType: String,
    val releaseDate: String?,
    val firstAirDate: String?,
    val posterPath: String?,
    val name: String?,
    val overview: String?,
    val title: String?,
)
