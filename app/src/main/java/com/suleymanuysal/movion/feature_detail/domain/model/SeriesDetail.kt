package com.suleymanuysal.movion.feature_detail.domain.model

import com.suleymanuysal.movion.feature_detail.data.remote.series_detail_dto.SeriesGenre
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity

data class SeriesDetail(
    val id: Int,
    val posterPath: String?,
    val name: String?,
    val firstAirdate: String?,
    val adult: Boolean?,
    val voteAverage: Double?,
    val originalLanguage: String?,
    val overview: String?,
    val genres: List<SeriesGenre>?,
    val numberOfSeasons : Int

)
fun SeriesDetail.toMyListEntity(): MyListEntity{
    return MyListEntity(id,"tv",
        null, firstAirdate,
        posterPath, name,null,
        voteAverage, null
    )
}
