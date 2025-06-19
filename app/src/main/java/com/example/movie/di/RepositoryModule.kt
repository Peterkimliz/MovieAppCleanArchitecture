package com.example.movie.di

import com.example.movie.movieList.data.repository.MovieRepositoryImpl
import com.example.movie.movieList.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

}