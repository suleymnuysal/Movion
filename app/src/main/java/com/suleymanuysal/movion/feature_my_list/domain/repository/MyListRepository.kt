package com.suleymanuysal.movion.feature_my_list.domain.repository

import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import kotlinx.coroutines.flow.Flow

interface MyListRepository {
    suspend fun getAllStoredItems(): Flow<List<MyListEntity>>
    suspend fun addToMyList(item: MyListEntity)
    suspend fun removeFromMyList(item: MyListEntity)
    suspend fun checkExisting(id: Int) : Flow<Int?>
}