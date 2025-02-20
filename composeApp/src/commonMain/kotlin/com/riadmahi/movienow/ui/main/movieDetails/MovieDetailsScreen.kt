package com.riadmahi.movienow.ui.main.movieDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsDescription
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsHeader
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsRatingAndVideo
import kotlinx.datetime.LocalDate

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(uiState) {
        MovieDetailsUiState.Loading -> { CircularProgressIndicator(modifier = Modifier.size(50.dp)) }
        is MovieDetailsUiState.Error -> { }
        is MovieDetailsUiState.Success -> {
            val movieDetails = (uiState as MovieDetailsUiState.Success).movieDetails
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    MovieDetailsHeader(
                        title = movieDetails.title,
                        year = "${movieDetails.releaseDate?.let {LocalDate.parse(it).year}}",
                        thumbnail = "https://image.tmdb.org/t/p/original/${movieDetails.posterPath}"
                    )
                    MovieDetailsRatingAndVideo(
                        numberRating = movieDetails.voteCount,
                        score = (movieDetails.voteAverage / 10).toFloat(),
                        textScore = "${(movieDetails.voteAverage * 10).toInt()}%"
                    )
                    movieDetails.overview?.let { MovieDetailsDescription(description = movieDetails.overview) }
                }
            }
        }
    }
}
