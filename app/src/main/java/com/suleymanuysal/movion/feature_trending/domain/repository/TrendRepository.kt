package com.suleymanuysal.movion.feature_trending.domain.repository

import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_trending.data.local.TrendEntity
import com.suleymanuysal.movion.feature_trending.data.remote.dto.TrendDto
import com.suleymanuysal.movion.feature_trending.domain.model.Trend
import kotlinx.coroutines.flow.Flow

interface TrendRepository {
    suspend fun getAllTrends(): TrendDto
    suspend fun getAllTrendsFromDb(type: String): Flow<List<TrendEntity>>
    suspend fun insertTrendsToDb(trends : List<TrendEntity>)
}