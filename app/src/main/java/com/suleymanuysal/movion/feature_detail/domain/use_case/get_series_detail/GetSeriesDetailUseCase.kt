package com.suleymanuysal.movion.feature_detail.domain.use_case.get_series_detail

import android.util.Log
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.toMovieDetail
import com.suleymanuysal.movion.feature_detail.data.remote.series_detail_dto.toSeriesDetail
import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail
import com.suleymanuysal.movion.feature_detail.domain.model.SeriesDetail
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetSeriesDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun executeGetSeriesDetail(seriesId: Int) : Flow<SeriesDetail> = flow {
        try {
            val movieDetail = detailRepository.getSeriesDetail(seriesId)
            emit(movieDetail.toSeriesDetail())

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}