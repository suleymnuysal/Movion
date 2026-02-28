package com.suleymanuysal.movion.feature_trending.data.remote.dto

import com.suleymanuysal.movion.feature_trending.data.local.TrendEntity
import com.suleymanuysal.movion.feature_trending.domain.model.Trend

data class TrendDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)


fun TrendDto.toTrendEntity() : List<TrendEntity>{
    return results.map { it -> TrendEntity(it.id,it.media_type,it.release_date
        ,it.first_air_date,it.poster_path,it.name,it.overview,it.title) }
}
fun TrendDto.toTrend() : List<Trend>{
    return results.map { it -> Trend(it.id,it.media_type,it.release_date
        ,it.first_air_date,it.poster_path,it.name,it.overview,it.title) }
}
