package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.CardSize
import com.riadmahi.movienow.ui.common.MovieRow
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.movie_section_now_playing
import movienow.composeapp.generated.resources.movie_section_popular
import movienow.composeapp.generated.resources.movie_section_upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExploreScreen(viewModel: ExploreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (uiState is ExploreUiState.Content) {
            item {
                MovieSection(
                    state = (uiState as ExploreUiState.Content).popularMovies,
                    title = stringResource(Res.string.movie_section_popular),
                    cardSize = CardSize.Medium
                )
            }

            item {
                MovieSection(
                    state = (uiState as ExploreUiState.Content).nowPlayingMovies,
                    title = stringResource(Res.string.movie_section_now_playing)
                )
            }

            item {
                MovieSection(
                    state = (uiState as ExploreUiState.Content).upcomingMovies,
                    title = stringResource(Res.string.movie_section_upcoming)
                )
            }
        }
    }
}

@Composable
fun MovieSection(
    state: ExploreUiState.MovieListState,
    title: String,
    cardSize: CardSize = CardSize.Small,
) {
    when (state) {
        is ExploreUiState.MovieListState.Success -> {
            MovieRow(
                title = title,
                movies = state.movies,
                cardSize = cardSize
            )
        }
        is ExploreUiState.MovieListState.Loading -> {
            // Vous pouvez personnaliser le modifier selon vos besoins
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        is ExploreUiState.MovieListState.Error -> {
            Text(text = "Error: ${state.errorMessage}")
        }
    }
}