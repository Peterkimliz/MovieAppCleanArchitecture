package com.example.movie.movieList.presentation.movielist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MovieListPage(
    viewModel: MovieListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") })
        }
    ) {

            innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            if (viewModel.loadingMovies) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else if(viewModel.movies.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No movies were found")
                }
            }
            else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp)) {

                    itemsIndexed(items = viewModel.movies){
                        index,movie->
                        MovieItem(movie = movie,modifier = Modifier.clickable {  })
                    }
                }
            }


        }
    }
}