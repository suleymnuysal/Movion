package com.suleymanuysal.movion.feature_detail.domain.use_case.get_episodes

import android.util.Log
import com.suleymanuysal.movion.feature_detail.data.remote.episodes_detail_dto.toEpisodeList
import com.suleymanuysal.movion.feature_detail.domain.model.Episode
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun executeGetEpisodes(seriesId: Int, seasonNumber: Int) : Flow<List<Episode>> = flow {
        try {
            val episodes = detailRepository.getEpisodes(seriesId,seasonNumber).toEpisodeList()
            emit(episodes)

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}