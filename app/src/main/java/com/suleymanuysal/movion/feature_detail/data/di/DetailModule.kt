package com.suleymanuysal.movion.feature_detail.data.di

import com.suleymanuysal.movion.feature_detail.data.remote.DetailApi
import com.suleymanuysal.movion.feature_detail.data.repository.DetailRepositoryImpl
import com.suleymanuysal.movion.feature_detail.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Provides
    @Singleton
    fun provideDetailApi(retrofit: Retrofit) : DetailApi{
        return retrofit.create(DetailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(detailApi: DetailApi) : DetailRepository{
        return DetailRepositoryImpl(detailApi)
    }

}