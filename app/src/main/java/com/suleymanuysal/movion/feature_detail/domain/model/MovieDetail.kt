package com.suleymanuysal.movion.feature_detail.domain.model

import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.MovieGenre
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import okhttp3.MediaType

data class MovieDetail(
    val id: Int,
    val posterPath: String?,
    val title: String?,
    val releaseDate: String?,
    val adult: Boolean?,
    val runtime: Int?,
    val voteAverage: Double?,
    val originalLanguage: String?,
    val overview: String?,
    val genres: List<MovieGenre>?,
)

fun MovieDetail.toMyListEntity(): MyListEntity{
    return MyListEntity(id,"movie",
        releaseDate, null,
        posterPath, null,title,
        voteAverage, runtime
    )
}

