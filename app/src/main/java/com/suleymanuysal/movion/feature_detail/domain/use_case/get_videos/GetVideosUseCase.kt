package com.suleymanuysal.movion.feature_detail.domain.use_case.get_videos

import android.util.Log
import com.suleymanuysal.movion.feature_detail.data.remote.videos_dto.toVideo
import com.suleymanuysal.movion.feature_detail.domain.model.Video
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    val detailRepository: DetailRepository
) {
    fun executeGetVideos(type: String, id: Int) : Flow<List<Video>> = flow {
        try {
            val videos = detailRepository.getVideos(type,id)
            emit(videos.toVideo())
        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }
}