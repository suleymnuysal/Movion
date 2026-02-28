package com.suleymanuysal.movion.feature_movie.data.di

import com.suleymanuysal.movion.core.core_database.MovionDatabase
import com.suleymanuysal.movion.feature_movie.data.local.MovieDao
import com.suleymanuysal.movion.feature_movie.data.remote.MovieAPI
import com.suleymanuysal.movion.feature_movie.data.repository.MovieRepositoryImpl
import com.suleymanuysal.movion.feature_movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieAPI{
        return retrofit.create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovionDatabase): MovieDao{
        return db.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieAPI: MovieAPI,movieDao: MovieDao) : MovieRepository{
        return MovieRepositoryImpl(movieAPI,movieDao)
    }

}