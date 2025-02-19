package com.riadmahi.movienow.ui.main.explore

import com.riadmahi.movienow.data.model.Movie

sealed interface ExploreUiState {
    data object Loading : ExploreUiState
    data class Content(
        val popularMovies: MovieListState,
        val nowPlayingMovies: MovieListState
    ) : ExploreUiState

    data class Error(val errorMessage: String) : ExploreUiState

    sealed interface MovieListState {
        data object Loading : MovieListState
        data class Success(val movies: List<Movie>) : MovieListState
        data class Error(val errorMessage: String) : MovieListState
    }
}