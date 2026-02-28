package com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.use_case.add_to_my_list.AddToMyListUseCase
import com.suleymanuysal.movion.feature_my_list.domain.use_case.check_existing.CheckExistingUseCase
import com.suleymanuysal.movion.feature_my_list.domain.use_case.get_all_stored_items.GetAllStoredItemsUseCase
import com.suleymanuysal.movion.feature_my_list.domain.use_case.remove_from_my_list.RemoveFromMyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val addToMyListUseCase: AddToMyListUseCase,
    private val getAllStoredItems: GetAllStoredItemsUseCase,
    private val checkExistingUseCase: CheckExistingUseCase,
    private val removeFromMyListUseCase: RemoveFromMyListUseCase
) : ViewModel(){

    private val _myList = MutableStateFlow<List<MyListEntity>>(emptyList())
    val myList : StateFlow<List<MyListEntity>> = _myList

    private val _isStored = MutableStateFlow(false)
    val isStored : StateFlow<Boolean> = _isStored

    init {
        getAllMyList()
    }

    fun addToMyList(item: MyListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            addToMyListUseCase.executeAddToMyList(item).collect()
        }
    }

    fun removeFromMyList(item: MyListEntity){
        viewModelScope.launch(Dispatchers.IO){
            removeFromMyListUseCase.executeRemoveFromMyList(item).collect()
        }
    }

    fun getAllMyList(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllStoredItems.executeGetAllStoredItems().collect { myList ->
                _myList.value = myList

            }
        }
    }

    fun checkExisting(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            checkExistingUseCase.executeCheckExisting(id).collect { itemId ->
                if(itemId != null) _isStored.value = true else _isStored.value = false
            }
        }
    }
}