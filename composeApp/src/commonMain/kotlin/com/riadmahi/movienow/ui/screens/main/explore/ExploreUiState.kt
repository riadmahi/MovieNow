package com.riadmahi.movienow.ui.main.explore

import com.riadmahi.movienow.ui.common.MovieListState

sealed interface ExploreUiState {
    data object Loading : ExploreUiState
    data class Content(
        val popularMovies: MovieListState,
        val nowPlayingMovies: MovieListState,
        val upcomingMovies: MovieListState,
        val topRatedMovies: MovieListState
    ) : ExploreUiState

    data class Error(val errorMessage: String) : ExploreUiState
}