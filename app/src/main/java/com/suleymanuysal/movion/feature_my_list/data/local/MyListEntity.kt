package com.suleymanuysal.movion.feature_my_list.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suleymanuysal.movion.feature_my_list.domain.model.MyList

@Entity(tableName = "MyList")
data class MyListEntity(
    @ColumnInfo(name = "item_id")
    val id: Int,
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "runtime")
    val runtime: Int?,
    @ColumnInfo(name = "saved_at")
    val savedAt: Long = System.currentTimeMillis()
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var tableId : Int = 0
}
fun MyListEntity.toMyList(): MyList{
    return MyList(id,mediaType,releaseDate,firstAirDate,posterPath,name,title,voteAverage)
}
