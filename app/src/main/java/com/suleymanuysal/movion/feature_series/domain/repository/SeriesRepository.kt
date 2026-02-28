package com.suleymanuysal.movion.feature_series.domain.repository

import com.suleymanuysal.movion.feature_series.data.local.SeriesEntity
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getAllSeriesListsFromApi(category: SeriesCategory): SeriesDto
    suspend fun getAllTvCategoriesFromApi(category : SeriesCategory): SeriesDto
    suspend fun getAllSeriesFromDb(category: SeriesCategory) : Flow<List<SeriesEntity>>
    suspend fun insertAllSeries(series: List<SeriesEntity>)
}