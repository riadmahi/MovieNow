package com.riadmahi.movienow.ui.screens.main.bookmarks

import com.riadmahi.movienow.data.model.local.BookmarkWithMovies

interface BookmarksUiState {
    data object Loading: BookmarksUiState
    data class Success(val bookmarksLists: List<BookmarkWithMovies>): BookmarksUiState
    data class Error(val error: String): BookmarksUiState
}
