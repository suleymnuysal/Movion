package com.suleymanuysal.movion.feature_my_list.domain.use_case.check_existing

import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CheckExistingUseCase @Inject constructor(
    private val myListRepository: MyListRepository
) {
    fun executeCheckExisting(id: Int) : Flow<Int?> = flow {

        val storedItems = myListRepository.checkExisting(id)
        emitAll(storedItems)

    }.flowOn(Dispatchers.IO)
}
