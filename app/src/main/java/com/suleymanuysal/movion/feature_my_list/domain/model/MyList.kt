package com.suleymanuysal.movion.feature_my_list.domain.model

data class MyList(
    val id: Int,
    val mediaType: String?,
    val releaseDate: String?,
    val firstAirDate: String?,
    val posterPath: String?,
    val name: String?,
    val title: String?,
    val voteAverage: Double?,
)
