package com.suleymanuysal.movion.feature_detail.presentation.view_model

import android.renderscript.Type
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleymanuysal.movion.feature_detail.domain.model.Credit
import com.suleymanuysal.movion.feature_detail.domain.model.Episode
import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail
import com.suleymanuysal.movion.feature_detail.domain.model.SeriesDetail
import com.suleymanuysal.movion.feature_detail.domain.model.Video
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_credits.GetCreditsUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_episodes.GetEpisodesUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_related_movies.GetRelatedMoviesUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_related_series.GetRelatedSeriesUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_series_detail.GetSeriesDetailUseCase
import com.suleymanuysal.movion.feature_detail.domain.use_case.get_videos.GetVideosUseCase
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_series.domain.model.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getSeriesDetailUseCase: GetSeriesDetailUseCase,
    private val getRelatedMoviesUseCase: GetRelatedMoviesUseCase,
    private val getRelatedSeriesUseCase: GetRelatedSeriesUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getCreditsUseCase: GetCreditsUseCase,
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel(){

    private val _movieDetail = MutableStateFlow<MovieDetail>(
        MovieDetail(1, "",
        "","",false,125,25.05,"",
        "",emptyList()))
    val movieDetail : StateFlow<MovieDetail> = _movieDetail

    private val _seriesDetail = MutableStateFlow<SeriesDetail>(
        SeriesDetail(1, "",
            "","",false,125.5,"","",
            emptyList(),0))
    val serisDetail : StateFlow<SeriesDetail> = _seriesDetail

    private val _relatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val relatedMovies: StateFlow<List<Movie>> = _relatedMovies

    private val _relatedSeries = MutableStateFlow<List<Series>>(emptyList())
    val relatedSeries: StateFlow<List<Series>> = _relatedSeries

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes

    private val _credits = MutableStateFlow<List<Credit>>(emptyList())
    val credits: StateFlow<List<Credit>> = _credits

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos : StateFlow<List<Video>> = _videos


    fun getMovieDetail(movieId: Int){
        viewModelScope.launch {
            getMovieDetailUseCase.executeGetMovieDetail(movieId).collect { movieDetail ->
                _movieDetail.value = movieDetail
            }
        }
    }

    fun getSeriesDetail(seriesId: Int){
        viewModelScope.launch {
            getSeriesDetailUseCase.executeGetSeriesDetail(seriesId).collect { seriesDetail ->
                _seriesDetail.value = seriesDetail
            }
        }
    }

    fun getRelatedMovies(movieId: Int){
        viewModelScope.launch {
            getRelatedMoviesUseCase.executeGetRelatedMovies(movieId).collect { movies ->
                _relatedMovies.value = movies
            }
        }
    }

    fun getRelatedSeries(seriesId: Int){
        viewModelScope.launch {
            getRelatedSeriesUseCase.executeGetRelatedSeries(seriesId).collect { series ->
                _relatedSeries.value = series
            }
        }
    }

    fun getEpisodes(seriesId: Int, seasonNumber: Int){
        viewModelScope.launch {
            getEpisodesUseCase.executeGetEpisodes(seriesId,seasonNumber).collect { episodes ->
                _episodes.value = episodes
            }
        }
    }

    fun getCredits(type: String,id: Int){
        viewModelScope.launch {
            getCreditsUseCase.executeGetCredits(type,id).collect { credits ->
                _credits.value = credits
            }
        }
    }

    fun getVideos(type: String,id: Int){
        viewModelScope.launch {
            getVideosUseCase.executeGetVideos(type,id).collect { videos ->
                _videos.value = videos
            }
        }
    }

}