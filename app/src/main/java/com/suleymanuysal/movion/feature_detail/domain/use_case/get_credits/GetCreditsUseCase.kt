package com.suleymanuysal.movion.feature_detail.domain.use_case.get_credits

import android.util.Log
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_detail.data.remote.credits.toCredit
import com.suleymanuysal.movion.feature_detail.domain.model.Credit
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCreditsUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    fun executeGetCredits(type: String, id: Int) : Flow<List<Credit>> = flow {
        try {
           val credits = detailRepository.getCredits(type,id)
               .toCredit()
               .filter { it -> it.order < 9 }
           emit(credits)

        }catch (e: HttpException){
            Log.e("HTTP error",e.localizedMessage ?: "an unknown error accord")
        }catch (e: IOException){
            Log.e("IO error",e.localizedMessage ?: "an unknown error accord")
        }
    }.flowOn(Dispatchers.IO)
}