package com.suleymanuysal.movion.feature_movie.domain.use_case.get_all_movies

import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_movie.data.local.toMovie
import com.suleymanuysal.movion.feature_movie.data.remote.dto.toMovieEntityList
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import com.suleymanuysal.movion.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun executeGetAllMovies(category: MovieCategory): Flow<Resource<List<Movie>>> = flow {

        try {
            emit(Resource.Loading())
            val moviesFromApi = fetchMoviesFromApi(category)
            if(moviesFromApi.isNotEmpty()){
                emit(Resource.Success(moviesFromApi.map { it.toMovie() }))
            }else{
                emit(Resource.Error("error"))
            }

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "an unknown error accord"))
        }catch (e: IOException){
            emit(Resource.Error(message = "Could not reach internet"))
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun fetchMoviesFromApi(category: MovieCategory): List<MovieEntity>{

        return if( category == MovieCategory.NowPlaying
            || category == MovieCategory.Popular
            || category == MovieCategory.TopRated
            || category == MovieCategory.Upcoming) {

            movieRepository.getAllMovieListsFromApi(category).toMovieEntityList(category)

        }else{
            movieRepository.getAllMovieCategoriesFromApi(category).toMovieEntityList(category)
        }
    }

}