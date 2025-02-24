package com.riadmahi.movienow.ui.screens.main.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.ui.common.DefaultErrorScreen
import com.riadmahi.movienow.ui.common.MovieListState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingSection(
    trendingState: MovieListState,
    onMovieClicked: (Movie) -> Unit = { },
    onRetryClicked: () -> Unit = { }
) {
    when (trendingState) {
        is MovieListState.Error -> {
            DefaultErrorScreen(
                showRetry = true,
                retryClicked = { onRetryClicked() }
            )
        }
        MovieListState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        is MovieListState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1A0B13))
            ) {
                stickyHeader { TrendingHeader() }
                items(trendingState.movies) { movie ->
                    TrendingRowItem(movie = movie, onMovieClicked = { onMovieClicked(movie) })
                }
            }
        }
    }
}