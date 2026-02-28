package com.suleymanuysal.movion.feature_detail.domain.repository

import com.suleymanuysal.movion.feature_detail.data.remote.credits.CreditsDto
import com.suleymanuysal.movion.feature_detail.data.remote.episodes_detail_dto.EpisodesDto
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.MovieDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.series_detail_dto.SeriesDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.videos_dto.VideosDto
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto

interface DetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetailDto
    suspend fun getSeriesDetail(seriesId: Int): SeriesDetailDto
    suspend fun getRelatedMovies(movieId: Int) : MovieDto
    suspend fun getRelatedSeries(seriesId: Int) : SeriesDto
    suspend fun getEpisodes(seriesId: Int,seasonNumber: Int) : EpisodesDto
    suspend fun getCredits(type: String,id: Int) : CreditsDto
    suspend fun getVideos(type: String, id: Int) : VideosDto
}