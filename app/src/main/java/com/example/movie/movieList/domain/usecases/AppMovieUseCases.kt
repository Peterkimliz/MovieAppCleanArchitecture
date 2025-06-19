package com.example.movie.movieList.domain.usecases

data class AppMovieUseCases(
    val getMovieById: GetMovieById,
    val  getMoviesByCategory: GetMoviesByCategory
)