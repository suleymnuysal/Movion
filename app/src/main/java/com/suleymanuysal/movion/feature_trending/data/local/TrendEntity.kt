package com.suleymanuysal.movion.feature_trending.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suleymanuysal.movion.feature_trending.domain.model.Trend

@Entity(tableName = "trends")
data class TrendEntity (
    @ColumnInfo(name = "trend_id")
    val id: Int,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "overView")
    val overview: String?,
    @ColumnInfo(name = "title")
    val title: String?,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var tableId : Int = 0
}

fun TrendEntity.toTrend() : Trend{
    return Trend(id,mediaType,releaseDate,firstAirDate,posterPath,name,overview,title)
}