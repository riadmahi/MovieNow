package com.riadmahi.movienow.ui.main.movieDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.DefaultErrorScreen
import com.riadmahi.movienow.ui.common.MovieNowTopBar
import com.riadmahi.movienow.ui.main.movieDetails.components.*
import kotlinx.datetime.LocalDate

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val buttonState by viewModel.bookmarkButtonState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            MovieDetailsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

            is MovieDetailsUiState.Error -> {
                DefaultErrorScreen(
                    showRetry = true,
                    retryClicked = { viewModel.fetchMovie() }
                )
            }

            is MovieDetailsUiState.Content -> {
                val movieDetails = (uiState as MovieDetailsUiState.Content).movieDetails
                val watchProviders = (uiState as MovieDetailsUiState.Content).watchProviders
                val casting = (uiState as MovieDetailsUiState.Content).casting

                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 120.dp)) {
                    item {
                        MovieDetailsHeader(
                            title = movieDetails.title,
                            year = "${movieDetails.releaseDate?.let { LocalDate.parse(it).year }}",
                            thumbnail = "https://image.tmdb.org/t/p/original/${movieDetails.posterPath}",
                        )
                        MovieDetailsRatingAndBookmark(
                            numberRating = movieDetails.voteCount,
                            score = (movieDetails.voteAverage / 10).toFloat(),
                            textScore = "${(movieDetails.voteAverage * 10).toInt()}%",
                            buttonState = buttonState,
                            toggle = { viewModel.toggleBookmark() }
                        )
                        watchProviders?.let { MovieDetailsWatchProviders(watchProviders) }
                        movieDetails.overview?.let { MovieDetailsDescription(description = movieDetails.overview) }
                        MovieDetailsCategories(movieDetails.genres)
                        casting?.let { MovieDetailsCasting(casting) }
                    }
                }
            }
        }
        MovieNowTopBar(navigateBack = { navigateBack() })
    }
}
