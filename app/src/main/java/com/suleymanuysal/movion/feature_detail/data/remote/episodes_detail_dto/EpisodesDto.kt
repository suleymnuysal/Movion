package com.suleymanuysal.movion.feature_detail.data.remote.episodes_detail_dto

import com.suleymanuysal.movion.feature_detail.domain.model.Episode

data class EpisodesDto(
    val _id: String,
    val air_date: String,
    val episodes: List<Episodes>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)
fun EpisodesDto.toEpisodeList(): List<Episode> {
    return episodes.map { it -> Episode(it.id,it.name,
        it.overview,poster_path,it.runtime,
        it.season_number,it.episode_number)
    }
}