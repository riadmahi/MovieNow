package com.riadmahi.movienow.ui.screens.main.bookmarks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.ui.common.AsyncTmdbImage
import com.riadmahi.movienow.ui.common.CardSize

@Composable
fun BookmarkMovie(
    movie: MoviePreview,
    onMovieClick: () -> Unit = { }
) {
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
            AsyncTmdbImage(
                modifier = Modifier.fillMaxSize(),
                posterPath = movie.posterPath
            )
        }

        Text(
            movie.title,
            fontWeight = FontWeight.Medium
        )
    }
}