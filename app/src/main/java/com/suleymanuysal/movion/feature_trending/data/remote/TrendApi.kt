package com.suleymanuysal.movion.feature_trending.data.remote

import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_trending.data.remote.dto.TrendDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendApi {

    @GET(Constants.TRENDING_ENDPOINT)
    suspend fun getAllTrends(
        @Query("api_key")  apiKey : String = Constants.API_KEY,
        @Query("include_adult") adult: String = Constants.DEFAULT_ADULT,
        @Query("language") language : String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page : Int = Constants.DEFAULT_PAGE_NUMBER
    ) : TrendDto
}