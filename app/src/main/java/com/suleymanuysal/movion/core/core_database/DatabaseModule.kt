package com.suleymanuysal.movion.core.core_database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): MovionDatabase{
        return Room.databaseBuilder(context= context.applicationContext,
            klass = MovionDatabase::class.java,
            name = "MovionDatabase")
            .fallbackToDestructiveMigration().build()

    }
}