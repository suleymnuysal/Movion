package com.suleymanuysal.movion.feature_trending.domain.use_case.get_all_trends

import android.util.Log
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_trending.data.local.toTrend
import com.suleymanuysal.movion.feature_trending.data.remote.dto.toTrend
import com.suleymanuysal.movion.feature_trending.data.remote.dto.toTrendEntity
import com.suleymanuysal.movion.feature_trending.domain.model.Trend
import com.suleymanuysal.movion.feature_trending.domain.repository.TrendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllTrendsUseCase @Inject constructor(
    private val trendRepository: TrendRepository
){
    fun executeAllTrends() : Flow<Resource<List<Trend>>> = flow {

        try {
            emit(Resource.Loading())
            val trendingMoviesFromApi = trendRepository.getAllTrends().toTrend()
            if(trendingMoviesFromApi.isNotEmpty()){
                emit(Resource.Success(trendingMoviesFromApi))
            }else{
                emit(Resource.Error("empty List",emptyList()))
            }

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "an unknown error accord"))
        }catch (e: IOException){
            emit(Resource.Error(message = "Could not reach internet"))
        }

    }.flowOn(Dispatchers.IO)
}