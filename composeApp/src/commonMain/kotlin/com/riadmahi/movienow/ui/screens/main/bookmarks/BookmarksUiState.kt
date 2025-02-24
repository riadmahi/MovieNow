package com.riadmahi.movienow.ui.screens.main.bookmarks

import com.riadmahi.movienow.data.model.MoviePreview

interface BookmarksUiState {
    data object Loading: BookmarksUiState
    data class Success(val bookmarkList: List<MoviePreview>): BookmarksUiState
    data class Error(val error: String): BookmarksUiState
}
