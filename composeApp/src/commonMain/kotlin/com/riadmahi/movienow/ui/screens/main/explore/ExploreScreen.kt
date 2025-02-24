package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.ui.common.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.movie_section_now_playing
import movienow.composeapp.generated.resources.movie_section_popular
import movienow.composeapp.generated.resources.movie_section_top_rated
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    onNavigateToMovieDetails: (Movie) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        when(uiState) {
            is ExploreUiState.Content -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    item {
                        Carousel(
                            state = (uiState as ExploreUiState.Content).upcomingMovies,
                            onMovieClick = { onNavigateToMovieDetails(it) }
                        )
                        MovieSection(
                            state = (uiState as ExploreUiState.Content).popularMovies,
                            title = stringResource(Res.string.movie_section_popular),
                            cardSize = CardSize.Medium,
                            onMovieClick = { onNavigateToMovieDetails(it) }
                        )
                        MovieSection(
                            state = (uiState as ExploreUiState.Content).nowPlayingMovies,
                            title = stringResource(Res.string.movie_section_now_playing),
                            onMovieClick = { onNavigateToMovieDetails(it) }
                        )
                        MovieSection(
                            state = (uiState as ExploreUiState.Content).topRatedMovies,
                            title = stringResource(Res.string.movie_section_top_rated),
                            onMovieClick = { onNavigateToMovieDetails(it) }
                        )
                    }
                }
            }
            is ExploreUiState.Error -> {
                DefaultErrorScreen(
                    showRetry = true,
                    retryClicked = { viewModel.fetchMovies() }
                )
            }
            ExploreUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }
        }
        Canvas(
            modifier = Modifier.fillMaxWidth().height(25.dp).align(Alignment.BottomCenter),
            onDraw = {
                drawRect(
                    Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                )
            }
        )
    }
}

@Composable
fun MovieSection(
    state: MovieListState,
    title: String,
    cardSize: CardSize = CardSize.Small,
    onMovieClick: (Movie) -> Unit = {}
) {
    when (state) {
        is MovieListState.Success -> {
            MovieRow(
                title = title,
                movies = state.movies,
                cardSize = cardSize,
                onMovieClick = { onMovieClick(it) }
            )
        }
        is MovieListState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }
        is MovieListState.Error -> {
            DefaultErrorScreen()
        }
    }
}