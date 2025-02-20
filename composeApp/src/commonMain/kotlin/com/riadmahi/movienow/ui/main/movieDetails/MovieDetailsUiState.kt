package com.riadmahi.movienow.ui.main.movieDetails

import com.riadmahi.movienow.data.model.MovieDetails

sealed interface MovieDetailsUiState {
    data object Loading: MovieDetailsUiState
    data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState
    data class Error(val errorMessage: String) : MovieDetailsUiState
}
