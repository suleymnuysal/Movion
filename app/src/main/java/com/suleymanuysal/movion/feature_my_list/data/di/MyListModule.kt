package com.suleymanuysal.movion.feature_my_list.data.di

import com.suleymanuysal.movion.core.core_database.MovionDatabase
import com.suleymanuysal.movion.feature_my_list.data.local.MyListDao
import com.suleymanuysal.movion.feature_my_list.data.repository.MyListRepositoryImpl
import com.suleymanuysal.movion.feature_my_list.domain.repository.MyListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyListModule {

    @Provides
    @Singleton
    fun provideMyListDao(db: MovionDatabase): MyListDao{
        return  db.myListDao()
    }

    @Provides
    @Singleton
    fun provideMyListRepository(myListDao: MyListDao): MyListRepository{
        return MyListRepositoryImpl(myListDao)
    }
}