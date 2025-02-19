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
import movienow.composeapp.generated.resources.movie_now_playing
import movienow.composeapp.generated.resources.movie_section_popular
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExploreScreen(viewModel: ExploreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        if (uiState is ExploreUiState.Content) {
            item {
                when (val popularState = (uiState as ExploreUiState.Content).popularMovies) {
                    is ExploreUiState.MovieListState.Success -> {
                        MovieRow(
                            title = stringResource(Res.string.movie_section_popular),
                            movies = popularState.movies,
                            cardSize = CardSize.Medium
                        )
                    }

                    is ExploreUiState.MovieListState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    is ExploreUiState.MovieListState.Error -> {
                        Text("Error: ${popularState.errorMessage}")
                    }
                }
            }

            item {
                when (val nowPlayingState = (uiState as ExploreUiState.Content).nowPlayingMovies) {
                    is ExploreUiState.MovieListState.Success -> {
                        MovieRow(
                            title = stringResource(Res.string.movie_now_playing),
                            movies = nowPlayingState.movies,
                            cardSize = CardSize.Small
                        )
                    }

                    is ExploreUiState.MovieListState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    is ExploreUiState.MovieListState.Error -> {
                        Text("Error: ${nowPlayingState.errorMessage}")
                    }
                }
            }
        }
    }
}