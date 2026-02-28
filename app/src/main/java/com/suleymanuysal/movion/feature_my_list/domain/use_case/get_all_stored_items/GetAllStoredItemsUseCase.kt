package com.suleymanuysal.movion.feature_my_list.domain.use_case.get_all_stored_items

import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllStoredItemsUseCase @Inject constructor(
    private val myListRepository: MyListRepository
) {
    fun executeGetAllStoredItems() : Flow<List<MyListEntity>> = flow {

        val storedItems = myListRepository.getAllStoredItems()
        emitAll(storedItems)

    }.flowOn(Dispatchers.IO)
}