package com.suleymanuysal.movion.feature_detail.domain.use_case.get_related_series

import android.util.Log
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import com.suleymanuysal.movion.feature_movie.data.remote.dto.toMovieList
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_series.data.remote.dto.toSeriesList
import com.suleymanuysal.movion.feature_series.domain.model.Series
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetRelatedSeriesUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun executeGetRelatedSeries(seriesId: Int) : Flow<List<Series>> = flow {
        try {
            val relatedSeries = detailRepository.getRelatedSeries(seriesId)
            emit(relatedSeries.toSeriesList())

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}