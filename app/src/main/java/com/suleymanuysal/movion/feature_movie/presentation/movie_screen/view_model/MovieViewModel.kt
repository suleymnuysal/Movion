package com.suleymanuysal.movion.feature_movie.presentation.movie_screen.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import com.suleymanuysal.movion.feature_movie.domain.use_case.get_all_movies.GetAllMoviesUseCase
import com.suleymanuysal.movion.feature_trending.domain.use_case.get_all_trends.GetAllTrendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getAllTrendsUseCase: GetAllTrendsUseCase

): ViewModel() {

    private val _latestMovie = MutableStateFlow(Movie(0,""))
    val latestMovie : StateFlow<Movie> = _latestMovie

    private val _movies = MutableStateFlow(MovieState())
    val movies : StateFlow<MovieState> = _movies

    init {
       getLatestMovie()

       getAllMovies(
        MovieCategory.NowPlaying, MovieCategory.Popular,
        MovieCategory.TopRated, MovieCategory.Upcoming,
        MovieCategory.Action, MovieCategory.Adventure,
        MovieCategory.Comedy, MovieCategory.Thriller,
        MovieCategory.Documentary, MovieCategory.Drama,
        MovieCategory.Family, MovieCategory.Fantasy,
        MovieCategory.History, MovieCategory.Horror,
        MovieCategory.Mystery, MovieCategory.Love,
        MovieCategory.Science, MovieCategory.TvFilm,
        MovieCategory.War, MovieCategory.Western
       )


    }   

    fun getAllMovies(vararg category: MovieCategory){
        category.forEach { category ->
            viewModelScope.launch {
                getAllMoviesUseCase.executeGetAllMovies(category).collect { state ->
                   when(state){
                       is Resource.Loading -> {
                           _movies.value = _movies.value.copy(isLoading = true)
                       }
                       is Resource.Error -> {
                           _movies.value = _movies.value.copy(isLoading = false,
                               error = state.message ?: "unexpected error"
                           )
                       }
                       is Resource.Success -> {
                           _movies.value = _movies.value.copy(movies = _movies.value.movies + (category to state.data!!),
                               isLoading = false
                           )

                       }
                   }
                }
            }
        }

    }

    fun getLatestMovie() {
        viewModelScope.launch {
            getAllTrendsUseCase.executeAllTrends().collect { trends ->
                val latest = trends.data?.firstOrNull { it -> it.mediaType == "movie" }
                latest?.let { it
                    _latestMovie.value = Movie(it.id,it.posterPath)
                }

            }
        }
    }




}