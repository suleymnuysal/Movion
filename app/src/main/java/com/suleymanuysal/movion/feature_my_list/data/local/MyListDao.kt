package com.suleymanuysal.movion.feature_my_list.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyListDao {

    @Query("SELECT * FROM MyList ORDER BY saved_at DESC")
    fun getAllStoredItems() : Flow<List<MyListEntity>>

    @Query("SELECT item_id FROM MyList WHERE item_id == :id LIMIT 1")
    fun checkExisting(id: Int) : Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToMyList(item : MyListEntity)

    @Delete
    suspend fun removeFromMyList(item: MyListEntity)
}