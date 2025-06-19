package com.example.movie.movieList.domain.repositories

import com.example.movie.movieList.domain.models.Movie
import com.example.movie.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMoviesByCategory(
        page: Int,
        forceFetchFromApi: Boolean,
        category: String
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovieById(id: Int): Flow<Resource<Movie>>

}