package com.example.movie.movieList.presentation.movielist
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.movieList.domain.models.Movie
import com.example.movie.movieList.domain.usecases.AppMovieUseCases
import com.example.movie.utils.Constants.POPULAR
import com.example.movie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val appMovieUseCases: AppMovieUseCases
):ViewModel() {
    var loadingMovies by mutableStateOf(false)
        private set

    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            appMovieUseCases.getMoviesByCategory(
                page = 1,
                category = POPULAR,
                forceFetchFromApi = false
            ).collect{ resource->

                when(resource){
                    is Resource.Error -> {
                        Log.d("MovieData",resource.message.toString())
                        loadingMovies=false
                    }
                    is Resource.Loading ->{
                        loadingMovies=resource.isLoading
                    }
                    is Resource.Success ->{
                        movies=resource.data
                    }
                }



            }


        }
    }

}