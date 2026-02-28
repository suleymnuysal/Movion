package com.suleymanuysal.movion.feature_my_list.data.repository

import com.suleymanuysal.movion.feature_my_list.data.local.MyListDao
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyListRepositoryImpl @Inject constructor(
    private val myListDao: MyListDao
) : MyListRepository {

    override suspend fun getAllStoredItems(): Flow<List<MyListEntity>> {
        return myListDao.getAllStoredItems()
    }

    override suspend fun checkExisting(id: Int): Flow<Int?> {
        return myListDao.checkExisting(id)
    }

    override suspend fun addToMyList(item: MyListEntity) {
        myListDao.addToMyList(item)
    }

    override suspend fun removeFromMyList(item: MyListEntity) {
        myListDao.removeFromMyList(item)
    }


}