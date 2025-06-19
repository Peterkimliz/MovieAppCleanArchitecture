package com.example.movie.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.movieList.data.local.MovieDatabase
import com.example.movie.movieList.data.remote.MovieApi
import com.example.movie.movieList.domain.repositories.MovieRepository
import com.example.movie.movieList.domain.usecases.AppMovieUseCases
import com.example.movie.movieList.domain.usecases.GetMovieById
import com.example.movie.movieList.domain.usecases.GetMoviesByCategory
import com.example.movie.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }


    @Provides
    @Singleton

    fun providesMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movie"
        ).build()

    }

    @Provides
    @Singleton

    fun providesUseCases(repository: MovieRepository): AppMovieUseCases {
        return AppMovieUseCases(
            getMoviesByCategory = GetMoviesByCategory(repository = repository),
            getMovieById = GetMovieById(repository = repository)
        )

    }


}