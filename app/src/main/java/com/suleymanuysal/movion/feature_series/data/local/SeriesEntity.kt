package com.suleymanuysal.movion.feature_series.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suleymanuysal.movion.feature_series.domain.model.Series

@Entity(tableName = "Series")
data class SeriesEntity(
    @ColumnInfo(name = "series_id")
    val id: Int,
    @ColumnInfo(name = "series_category")
    val category: String,
    @ColumnInfo(name = "poster_path")
    val posterPath : String?
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var tableId : Int = 0
}

fun SeriesEntity.toSeries(): Series{
    return Series(id = id, poster_path = posterPath)
}
