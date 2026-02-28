package com.suleymanuysal.movion.feature_detail.data.remote.videos_dto

import com.suleymanuysal.movion.feature_detail.domain.model.Video

data class VideosDto(
    val id: Int,
    val results: List<Result>
)
fun VideosDto.toVideo() : List<Video>{
    return results.map { it -> Video(it.site, it.type,it.key) }
}