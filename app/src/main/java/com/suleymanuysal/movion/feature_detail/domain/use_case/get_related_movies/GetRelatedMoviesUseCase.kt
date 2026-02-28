package com.suleymanuysal.movion.feature_detail.domain.use_case.get_related_movies

import android.util.Log
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.toMovieDetail
import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import com.suleymanuysal.movion.feature_movie.data.remote.dto.toMovieList
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetRelatedMoviesUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun executeGetRelatedMovies(movieId: Int) : Flow<List<Movie>> = flow {
        try {
            val relatedMovies = detailRepository.getRelatedMovies(movieId)
            emit(relatedMovies.toMovieList())

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}