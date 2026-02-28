package com.suleymanuysal.movion.feature_movie.domain.repository

import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllMovieListsFromApi(category: MovieCategory): MovieDto
    suspend fun getAllMovieCategoriesFromApi(category: MovieCategory) : MovieDto
    suspend fun getAllMoviesFromDb(category: MovieCategory): Flow<List<MovieEntity>>
    suspend fun insertAllMoviesToDb(movies: List<MovieEntity>)

}

