package com.riadmahi.movienow.ui.screens.main.bookmarks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.ui.common.CardSize
import com.riadmahi.movienow.utils.shimmerBrush

@Composable
fun BookmarkMovie(
    movie: MoviePreview,
    onMovieClick: () -> Unit = { }
) {
    var showShimmer by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onMovieClick()
            },
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.size(CardSize.Small.width, CardSize.Small.height),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        shimmerBrush(targetValue = 1300f, showShimmer = showShimmer)
                    ),
                model = "https://image.tmdb.org/t/p/original/${movie.posterPath}",
                contentDescription = "Movie thumbnail",
                contentScale = ContentScale.Crop,
                onSuccess = { showShimmer = false }
            )
        }

        Text(
            movie.title,
            fontWeight = FontWeight.Medium
        )
    }
}