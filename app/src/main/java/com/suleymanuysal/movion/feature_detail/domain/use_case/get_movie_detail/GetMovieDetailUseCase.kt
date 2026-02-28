package com.suleymanuysal.movion.feature_detail.domain.use_case.get_movie_detail

import android.util.Log
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.toMovieDetail
import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun executeGetMovieDetail(movieId: Int) : Flow<MovieDetail> = flow {
        try {
            val movieDetail = detailRepository.getMovieDetail(movieId)
            emit(movieDetail.toMovieDetail())

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}