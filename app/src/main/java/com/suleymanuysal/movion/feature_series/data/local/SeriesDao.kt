package com.suleymanuysal.movion.feature_series.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Query("SELECT * FROM series WHERE series_category = :category")
    fun getAlSeriesFromDb(category: String): Flow<List<SeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSeries(series: List<SeriesEntity>)
}