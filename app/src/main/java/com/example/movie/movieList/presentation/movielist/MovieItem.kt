package com.example.movie.movieList.presentation.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movie.R
import com.example.movie.movieList.domain.models.Movie
import com.example.movie.utils.Constants.IMAGE_BASE_URL

@Composable
fun MovieItem(movie: Movie, modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)

    ) {
        AsyncImage(
            model = "${IMAGE_BASE_URL}/${movie.backdrop_path}",
            contentDescription = "Profile image",
            modifier = modifier
                .padding(5.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_launcher_foreground),
            placeholder = painterResource(R.drawable.ic_launcher_foreground)
        )
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                movie.title,
                maxLines = 2,
                lineHeight = 10.sp,
                overflow = TextOverflow.Ellipsis, color = Color.Black
                ,style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 10.sp
                )
            )
        }
    }

}