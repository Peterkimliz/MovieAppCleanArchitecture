package com.example.movie.movieList.data.remote

import com.example.movie.movieList.data.remote.dtos.MovieResponseDto
import com.example.movie.utils.secrets.Secrets.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

  @GET("/movie/{category}")
   suspend fun getMovies(
      @Path("category") category:String,
      @Query("page") page:Int,
      @Query("api_key") apiKey:String=API_KEY): MovieResponseDto



}