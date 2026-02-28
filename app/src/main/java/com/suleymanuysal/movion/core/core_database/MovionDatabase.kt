package com.suleymanuysal.movion.core.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suleymanuysal.movion.feature_movie.data.local.MovieDao
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_my_list.data.local.MyListDao
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_series.data.local.SeriesDao
import com.suleymanuysal.movion.feature_series.data.local.SeriesEntity
import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_trending.data.local.TrendDao
import com.suleymanuysal.movion.feature_trending.data.local.TrendEntity

@Database(entities = [
    MovieEntity::class,
    SeriesEntity::class,
    TrendEntity::class,
    MyListEntity::class],
    version = 2 )
abstract class MovionDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao
    abstract fun trendDao(): TrendDao
    abstract fun myListDao(): MyListDao
}