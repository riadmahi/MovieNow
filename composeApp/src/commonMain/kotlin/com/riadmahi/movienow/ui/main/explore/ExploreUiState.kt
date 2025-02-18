package com.riadmahi.movienow.ui.main.explore

import com.riadmahi.movienow.data.model.Movie

sealed interface ExploreUiState {
    data object Loading : ExploreUiState
    data class Content(
        val popularMovies: PopularMovieState
    ) : ExploreUiState

    data class Error(val errorMessage: String) : ExploreUiState

    sealed interface PopularMovieState {
        data object Loading : PopularMovieState
        data class Success(val movies: List<Movie>) : PopularMovieState
        data class Error(val errorMessage: String) : PopularMovieState
    }
}