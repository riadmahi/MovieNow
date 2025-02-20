package com.riadmahi.movienow.ui.main.movieDetails

import com.riadmahi.movienow.data.model.CastMember
import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.data.model.WatchProvider

sealed interface MovieDetailsUiState {
    data object Loading: MovieDetailsUiState
    data class Content(
        val movieDetails: MovieDetails,
        val watchProviders: List<WatchProvider>? = null,
        val casting: List<CastMember>? = null

    ) : MovieDetailsUiState
    data class Error(val errorMessage: String) : MovieDetailsUiState
}
