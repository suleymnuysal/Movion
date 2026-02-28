package com.suleymanuysal.movion.feature_trending.data.di

import com.suleymanuysal.movion.core.core_database.MovionDatabase
import com.suleymanuysal.movion.feature_trending.data.local.TrendDao
import com.suleymanuysal.movion.feature_trending.data.remote.TrendApi
import com.suleymanuysal.movion.feature_trending.data.repository.TrendRepositoryImpl
import com.suleymanuysal.movion.feature_trending.domain.repository.TrendRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendModule {

    @Provides
    @Singleton
    fun provideTrendApi(retrofit: Retrofit): TrendApi{
        return retrofit.create(TrendApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendDao(db: MovionDatabase): TrendDao{
        return db.trendDao()
    }

    @Provides
    @Singleton
    fun provideTrendRepository(trendApi: TrendApi,trendDao: TrendDao) : TrendRepository{
        return TrendRepositoryImpl(trendApi,trendDao)
    }
}