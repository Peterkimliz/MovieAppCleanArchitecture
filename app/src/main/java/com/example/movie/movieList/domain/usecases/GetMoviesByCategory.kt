package com.example.movie.movieList.domain.usecases

import com.example.movie.movieList.domain.models.Movie
import com.example.movie.movieList.domain.repositories.MovieRepository
import com.example.movie.utils.Resource
import kotlinx.coroutines.flow.Flow


class GetMoviesByCategory(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(
        page: Int,
        forceFetchFromApi: Boolean,
        category: String
    ): Flow<Resource<List<Movie>>> {
        return repository.getMoviesByCategory(
            page = page,
            forceFetchFromApi = forceFetchFromApi,
            category = category
        );
    }
}