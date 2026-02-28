package com.suleymanuysal.movion.feature_series.data.repository

import com.suleymanuysal.movion.feature_series.data.local.SeriesDao
import com.suleymanuysal.movion.feature_series.data.local.SeriesEntity
import com.suleymanuysal.movion.feature_series.data.remote.SeriesAPI
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import com.suleymanuysal.movion.feature_series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesAPI: SeriesAPI,
    private val seriesDao: SeriesDao
) : SeriesRepository {
    override suspend fun getAllSeriesListsFromApi(category: SeriesCategory): SeriesDto {
        return seriesAPI.getAllTvLists(category.value)
    }

    override suspend fun getAllTvCategoriesFromApi(category: SeriesCategory): SeriesDto {
        return seriesAPI.getTvAllCategories(category = category.value)
    }

    override suspend fun getAllSeriesFromDb(category: SeriesCategory): Flow<List<SeriesEntity>> {
        return seriesDao.getAlSeriesFromDb(category.value)
    }

    override suspend fun insertAllSeries(series: List<SeriesEntity>) {
        seriesDao.insertAllSeries(series)
    }


}