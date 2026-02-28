package com.suleymanuysal.movion.feature_my_list.domain.use_case.remove_from_my_list

import android.util.Log
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.model.MyList
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveFromMyListUseCase  @Inject constructor(
    private val myListRepository: MyListRepository
) {
    fun executeRemoveFromMyList(item: MyListEntity) : Flow<MyListEntity> = flow {
        try {
            myListRepository.removeFromMyList(item)
        }catch (e: Exception){
            Log.e("error",e.localizedMessage ?: "an unknown error accord")
        }

    }
}