package com.suleymanuysal.movion.feature_series.data.di

import com.suleymanuysal.movion.core.core_database.MovionDatabase
import com.suleymanuysal.movion.feature_series.data.local.SeriesDao
import com.suleymanuysal.movion.feature_series.data.remote.SeriesAPI
import com.suleymanuysal.movion.feature_series.data.repository.SeriesRepositoryImpl
import com.suleymanuysal.movion.feature_series.domain.repository.SeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeriesModule {

    @Provides
    @Singleton
    fun provideSeriesApi(retrofit: Retrofit): SeriesAPI{
        return  retrofit.create(SeriesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSeriesDao(db: MovionDatabase): SeriesDao{
        return  db.seriesDao()
    }

    @Provides
    @Singleton
    fun provideSeriesRepository(seriesAPI: SeriesAPI,seriesDao: SeriesDao): SeriesRepository{
        return SeriesRepositoryImpl(seriesAPI,seriesDao)
    }
}