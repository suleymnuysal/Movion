package com.suleymanuysal.movion.feature_movie.data.repository

import android.util.Log
import com.suleymanuysal.movion.feature_movie.data.local.MovieDao
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_movie.data.remote.MovieAPI
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import com.suleymanuysal.movion.feature_movie.data.remote.dto.toMovieEntityList
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import com.suleymanuysal.movion.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi : MovieAPI,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getAllMovieListsFromApi(category: MovieCategory): MovieDto {
        return movieApi.getAllMoviesList(category = category.value)
    }

    override suspend fun getAllMovieCategoriesFromApi(category: MovieCategory): MovieDto {
        return movieApi.getAllMovieCategories(category = category.value)
    }

    override suspend fun getAllMoviesFromDb(category: MovieCategory): Flow<List<MovieEntity>> {
        return movieDao.getAllMoviesFromDb(category.value)

    }

    override suspend fun insertAllMoviesToDb(movies: List<MovieEntity>) {
        movieDao.insertAllMovies(movies)
    }


}