package com.example.movie.movieList.data.repository

import coil.network.HttpException
import com.example.movie.movieList.data.local.MovieDatabase
import com.example.movie.movieList.data.mappers.toMovie
import com.example.movie.movieList.data.mappers.toMovieEntity
import com.example.movie.movieList.data.remote.MovieApi
import com.example.movie.movieList.domain.models.Movie
import com.example.movie.movieList.domain.repositories.MovieRepository
import com.example.movie.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase
) : MovieRepository {
    override suspend fun getMoviesByCategory(
        page: Int,
        forceFetchFromApi: Boolean,
        category: String
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val localMovieData = db.movieDao.fetchMovieListByCategory(category)
            val fetchMovieFromDatabase = localMovieData.isNotEmpty() && !forceFetchFromApi

            if (fetchMovieFromDatabase) {
                emit(Resource.Success(
                    data = localMovieData.map { movieEntity -> movieEntity.toMovie(category) }

                ))
                emit(Resource.Loading(false))

                return@flow
            }

            val moviesFromApi = try {
                api.getMovies(category = category, page = page)
            } catch (e: IOException) {
                emit(Resource.Error(message = "Un expected Error has occurred"))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Network Error has occurred"))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Error(message = "Error has occurred"))
                return@flow
            }
            val movieEntityList = moviesFromApi
                .results.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            db.movieDao.upsertMovieList(movieList = movieEntityList)
            emit(Resource.Success(
                data = movieEntityList.map { movieEntity ->
                    movieEntity.toMovie(category)
                }
            ))
            emit(Resource.Loading(false))


        }


    }

    override suspend fun getMovieById(id: Int): Flow<Resource<Movie>> {
     return  flow {
         emit(Resource.Loading(true))
         val movie=db.movieDao.fetchMovieById(id)
         if(movie!=null){
          emit(Resource.Success(
              data = movie.toMovie(movie.category)
          ))

             emit(Resource.Loading(false))
             return@flow
         }

         emit(Resource.Error(message = "Movie With the id ${id} not found"))
         emit(Resource.Loading(false))
    }
    }

}


