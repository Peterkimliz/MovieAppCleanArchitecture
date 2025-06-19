package com.example.movie.movieList.domain.usecases

import com.example.movie.movieList.domain.models.Movie
import com.example.movie.movieList.domain.repositories.MovieRepository
import com.example.movie.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetMovieById  (
    private val repository: MovieRepository
){
    suspend operator fun invoke(id:Int):Flow<Resource<Movie>>{
        return repository.getMovieById(id);
    }
}