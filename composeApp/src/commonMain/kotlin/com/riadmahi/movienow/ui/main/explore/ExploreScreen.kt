package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.MovieCard

@Composable
fun ExploreScreen(viewModel: ExploreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState is ExploreUiState.Content) {
        when (val popularState = (uiState as ExploreUiState.Content).popularMovies) {
            is ExploreUiState.PopularMovieState.Success -> {
                LazyRow {
                    items(popularState.movies) { movie ->
                        MovieCard(movie = movie)
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
            is ExploreUiState.PopularMovieState.Loading -> {
                CircularProgressIndicator()
            }
            is ExploreUiState.PopularMovieState.Error -> {
                Text("Error: ${popularState.errorMessage}")
            }
        }
    }
}