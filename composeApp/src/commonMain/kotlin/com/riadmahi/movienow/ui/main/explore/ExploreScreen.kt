package com.riadmahi.movienow.ui.main.explore

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.MovieRow

@Composable
fun ExploreScreen(viewModel: ExploreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState is ExploreUiState.Content) {
        when (val popularState = (uiState as ExploreUiState.Content).popularMovies) {
            is ExploreUiState.PopularMovieState.Success -> {
                MovieRow(title = "Popular", popularState.movies)
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