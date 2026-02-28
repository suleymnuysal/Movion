package com.suleymanuysal.movion.feature_movie.data.remote

import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.feature_movie.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{movie_category}")
    suspend fun getAllMoviesList(
        @Path("movie_category") category: String,
        @Query("api_key")  apiKey : String = Constants.API_KEY,
        @Query("include_adult") adult: String = Constants.DEFAULT_ADULT,
        @Query("language") language : String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page : Int = Constants.DEFAULT_PAGE_NUMBER
    ) : MovieDto

    @GET(Constants.MOVIE_DISCOVER_ENDPOINT)
    suspend fun getAllMovieCategories(
        @Query("api_key")  apiKey : String = Constants.API_KEY,
        @Query("with_genres")  category: String,
        @Query("include_adult")  adult : String = Constants.DEFAULT_ADULT,
        @Query("language") language : String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page : Int = Constants.DEFAULT_PAGE_NUMBER
    ) : MovieDto


}