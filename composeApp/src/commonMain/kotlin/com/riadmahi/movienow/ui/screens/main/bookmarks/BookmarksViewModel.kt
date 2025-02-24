package com.riadmahi.movienow.ui.screens.main.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarksViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private var _uiState = MutableStateFlow<BookmarksUiState>(BookmarksUiState.Loading)
    val uiState: StateFlow<BookmarksUiState> = _uiState

    init {
        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        viewModelScope.launch {
            movieRepository.fetchBookmarks().collect { bookmarkList ->
                _uiState.value = BookmarksUiState.Success(bookmarkList = bookmarkList)
            }
        }
    }
}