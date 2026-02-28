package com.suleymanuysal.movion.feature_series.domain.use_case.get_all_tvs

import android.util.Log
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_series.data.local.SeriesEntity
import com.suleymanuysal.movion.feature_series.data.local.toSeries
import com.suleymanuysal.movion.feature_series.data.remote.dto.toSeriesEntity
import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import com.suleymanuysal.movion.feature_series.domain.repository.SeriesRepository
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

class GetAllTvsUseCase @Inject constructor(
   val seriesRepository: SeriesRepository
){
    fun executeGetAllTvs(category: SeriesCategory): Flow<Resource<List<Series>>> = flow {

        try {
            emit(Resource.Loading())
            val seriesFromApi = fetchSeriesFromApi(category)
            if(seriesFromApi.isNotEmpty()){
                emit(Resource.Success(seriesFromApi.map { it.toSeries() }))
            }else{
                emit(Resource.Error("error"))
            }

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun fetchSeriesFromApi(category: SeriesCategory): List<SeriesEntity>{

        return if( category == SeriesCategory.AiringToday
            || category == SeriesCategory.Popular
            || category == SeriesCategory.TopRated
            || category == SeriesCategory.OnTheAir) {

            seriesRepository.getAllSeriesListsFromApi(category).toSeriesEntity(category)

        }else{
            seriesRepository.getAllTvCategoriesFromApi(category).toSeriesEntity(category)
        }
    }
}