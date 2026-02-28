package com.suleymanuysal.movion.feature_detail.data.remote

import androidx.core.os.BuildCompat
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.feature_detail.data.remote.credits.CreditsDto
import com.suleymanuysal.movion.feature_detail.data.remote.episodes_detail_dto.EpisodesDto
import com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto.MovieDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.series_detail_dto.SeriesDetailDto
import com.suleymanuysal.movion.feature_detail.data.remote.videos_dto.VideosDto
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : MovieDetailDto

    @GET("tv/{series_id}")
    suspend fun getSeriesDetail(
        @Path("series_id") seriesId:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : SeriesDetailDto

    @GET("movie/{movie_id}/similar")
    suspend fun getRelatedMovies(
        @Path("movie_id") movieId:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : MovieDto

    @GET("tv/{series_id}/similar")
    suspend fun getRelatedSeries(
        @Path("series_id") seriesId:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : SeriesDto

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getEpisodes(
        @Path("series_id") seriesId:Int,
        @Path("season_number") seasonNumber:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : EpisodesDto

    @GET("{type}/{id}/credits")
    suspend fun getCredits(
        @Path("type") type:String,
        @Path("id") id:Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ) : CreditsDto

    @GET("{type}/{id}/videos")
    suspend fun getVideos(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Query("api_key")  apiKey : String = Constants.API_KEY
    ): VideosDto

}