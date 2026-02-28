package com.suleymanuysal.movion.feature_detail.data.remote.credits

import com.suleymanuysal.movion.feature_detail.domain.model.Credit

data class CreditsDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
fun CreditsDto.toCredit(): List<Credit>{
    return cast.map { it -> Credit(it.id,it.name,
        it.character,it.profile_path,
        it.order)
    }
}