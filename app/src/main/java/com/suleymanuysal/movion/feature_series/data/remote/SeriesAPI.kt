package com.suleymanuysal.movion.feature_series.data.remote

import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.feature_series.data.remote.dto.SeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesAPI {

    @GET("tv/{series_category}")
    suspend fun getAllTvLists(
        @Path("series_category") category:String,
        @Query("api_key")  apiKey : String = Constants.API_KEY,
        @Query("include_adult") adult: String = Constants.DEFAULT_ADULT,
        @Query("language") language : String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page : Int = Constants.DEFAULT_PAGE_NUMBER
    ): SeriesDto

    @GET(Constants.TV_DISCOVER_ENDPOINT)
    suspend fun getTvAllCategories(
        @Query("api_key")  apiKey : String = Constants.API_KEY,
        @Query("with_genres") category: String,
        @Query("include_adult") adult: String = Constants.DEFAULT_ADULT,
        @Query("language") language : String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page : Int = Constants.DEFAULT_PAGE_NUMBER
    ): SeriesDto




}