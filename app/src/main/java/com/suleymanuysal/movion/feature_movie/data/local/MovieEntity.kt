package com.suleymanuysal.movion.feature_movie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suleymanuysal.movion.feature_movie.domain.model.Movie

@Entity(tableName = "Movies")
data class MovieEntity(
    @ColumnInfo(name = "movie_id")
    val id: Int,
    @ColumnInfo(name = "movie_category")
    val category: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var tableId : Int = 0

}
fun MovieEntity.toMovie(): Movie{
    return Movie(id = id, posterPath = posterPath)
}

