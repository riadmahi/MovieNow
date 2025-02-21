package com.riadmahi.movienow.ui.main.search

import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.ui.common.MovieListState

sealed interface SearchUiState {
    data object Loading: SearchUiState
    data class Trending(val tredingMovieListState: MovieListState): SearchUiState
    data class Success(val movieFoundListState: MovieListState): SearchUiState
    data class Error(val errorMessage: String) : SearchUiState
}