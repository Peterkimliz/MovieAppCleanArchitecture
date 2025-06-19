package com.example.movie.movieList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList:List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id=:id")
    suspend fun fetchMovieById(id:Int) :MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE category=:category")
    suspend fun fetchMovieListByCategory(category:String ):List<MovieEntity>
}