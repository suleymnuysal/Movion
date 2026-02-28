package com.suleymanuysal.movion.feature_trending.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendDao {

    @Query("SELECT * FROM trends WHERE media_type == :type ")
    fun getAllTrendsFromDb(type: String): Flow<List<TrendEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTrends(trends: List<TrendEntity>)

}