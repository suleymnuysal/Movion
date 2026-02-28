package com.suleymanuysal.movion.feature_trending.data.repository

import com.suleymanuysal.movion.feature_trending.data.local.TrendDao
import com.suleymanuysal.movion.feature_trending.data.local.TrendEntity
import com.suleymanuysal.movion.feature_trending.data.remote.TrendApi
import com.suleymanuysal.movion.feature_trending.data.remote.dto.TrendDto
import com.suleymanuysal.movion.feature_trending.domain.repository.TrendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendRepositoryImpl @Inject constructor(
    private val trendApi: TrendApi,
    private val trendDao: TrendDao
) : TrendRepository {

    override suspend fun getAllTrends(): TrendDto {
        return trendApi.getAllTrends()
    }

    override suspend fun getAllTrendsFromDb(type: String): Flow<List<TrendEntity>> {
        return trendDao.getAllTrendsFromDb(type)
    }

    override suspend fun insertTrendsToDb(trends: List<TrendEntity>) {
        return trendDao.insertAllTrends(trends)
    }
}