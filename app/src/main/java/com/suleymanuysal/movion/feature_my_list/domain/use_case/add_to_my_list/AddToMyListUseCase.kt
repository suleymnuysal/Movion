package com.suleymanuysal.movion.feature_my_list.domain.use_case.add_to_my_list

import android.util.Log
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.model.MyList
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddToMyListUseCase @Inject constructor(
    private val myListRepository: MyListRepository
) {
    fun executeAddToMyList(item: MyListEntity) : Flow<List<MyList>> = flow {
        try {
            myListRepository.addToMyList(item)
        }catch (e: Exception){
            Log.e("error",e.localizedMessage ?: "an unknown error accord")
        }

    }
}