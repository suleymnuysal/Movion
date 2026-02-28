package com.suleymanuysal.movion.feature_movie.data.remote.dto

import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import com.suleymanuysal.movion.feature_trending.domain.model.Trend
import okhttp3.internal.notifyAll

data class MovieDto(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieDto.toMovieEntityList(category: MovieCategory): List<MovieEntity>{
    return results.map { it -> MovieEntity(
        it.id,
        category.value,
        it.poster_path)
    }
}
fun MovieDto.toMovieList(): List<Movie>{
    return results.map { it -> Movie(it.id, it.poster_path) }
}



