package com.suleymanuysal.movion.feature_detail.data.remote.movie_detail_dto

import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres: List<MovieGenre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailDto.toMovieDetail() : MovieDetail{
    return MovieDetail(
         id = id, posterPath = poster_path, title = title,
        releaseDate = release_date, adult =  adult,runtime = runtime,
        voteAverage =  vote_average, originalLanguage =  original_language,
        overview =  overview, genres = genres
    )
}