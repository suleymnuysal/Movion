package com.suleymanuysal.movion.feature_movie.presentation.movie_screen.view_model

import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory

data class MovieState(
    var isLoading : Boolean = false,
    var movies : Map<MovieCategory,List<Movie>>  = emptyMap(),
    var error : String = "an unexcepted error accord"

)
