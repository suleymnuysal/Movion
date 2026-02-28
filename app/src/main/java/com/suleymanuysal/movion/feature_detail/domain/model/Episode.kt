package com.suleymanuysal.movion.feature_detail.domain.model

data class Episode (
    val id : Int,
    val episodeName: String,
    val overview: String,
    val posterPath: String,
    val runtime: Any?,
    val seasonNumber: Int,
    val episodeNumber: Int,

)