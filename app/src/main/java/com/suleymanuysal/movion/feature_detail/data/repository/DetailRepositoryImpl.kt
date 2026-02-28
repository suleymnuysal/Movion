package com.suleymanuysal.movion.feature_detail.data.repository

import com.suleymanuysal.movion.feature_detail.data.remote.DetailApi
import com.suleymanuysal.movion.feature_detail.data.remote.credits.CreditsDto
import com.suleymanuysal.movion.feature_detail.data.remote.episodes_detail_dto.EpisodesDto
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.MovieDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.series_detail_dto.SeriesDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.videos_dto.VideosDto
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailApi: DetailApi
) : DetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetailDto {
        return detailApi.getMovieDetail(movieId)
    }

    override suspend fun getSeriesDetail(seriesId: Int): SeriesDetailDto {
        return detailApi.getSeriesDetail(seriesId)
    }

    override suspend fun getRelatedMovies(movieId: Int): MovieDto {
        return detailApi.getRelatedMovies(movieId)
    }

    override suspend fun getRelatedSeries(seriesId: Int): SeriesDto {
        return detailApi.getRelatedSeries(seriesId)
    }

    override suspend fun getEpisodes(seriesId: Int, seasonNumber: Int): EpisodesDto {
        return detailApi.getEpisodes(seriesId,seasonNumber)
    }

    override suspend fun getCredits(type: String, id: Int): CreditsDto {
        return detailApi.getCredits(type,id)
    }

    override suspend fun getVideos(type: String, id: Int): VideosDto {
        return detailApi.getVideos(type,id)
    }

}