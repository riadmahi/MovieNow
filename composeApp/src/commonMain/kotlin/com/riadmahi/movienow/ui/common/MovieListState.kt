package com.riadmahi.movienow.ui.common

import com.riadmahi.movienow.data.model.Movie

sealed interface MovieListState {
    data object Loading : MovieListState
    data class Success(val movies: List<Movie>) : MovieListState
    data class Error(val errorMessage: String) : MovieListState
}