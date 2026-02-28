package com.suleymanuysal.movion.feature_series.data.remote.dto

import com.suleymanuysal.movion.feature_series.data.local.SeriesEntity
import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import com.suleymanuysal.movion.feature_trending.domain.model.Trend

data class SeriesDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

fun SeriesDto.toSeriesEntity(category: SeriesCategory): List<SeriesEntity>{
    return results.map { it -> SeriesEntity(
        it.id,
        category.value,
        it.poster_path)
    }
}
fun SeriesDto.toSeriesList(): List<Series>{
    return results.map { it -> Series(it.id, it.poster_path) }
}
