package com.riadmahi.movienow.ui.screens.main.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.data.model.local.BookmarkList
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
            movieRepository.fetchBookmarks().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _uiState.value = BookmarksUiState.Loading
                    is Resource.Success -> _uiState.value = BookmarksUiState.Success(bookmarksLists = resource.data)
                    is Resource.Error -> _uiState.value = BookmarksUiState.Error(resource.error ?: "Unknown error")
                }
            }
        }
    }

    fun createBookmark(name: String) {
        viewModelScope.launch {
            movieRepository.createBookmark(name).collect { resource ->
                when (resource) {
                    is Resource.Loading -> { }
                    is Resource.Success -> fetchBookmarks()
                    is Resource.Error -> _uiState.value = BookmarksUiState.Error(resource.error ?: "Unknown error")
                }
            }
        }
    }

    fun deleteBookmark(bookmark: BookmarkList) {
        viewModelScope.launch {
            movieRepository.deleteBookmark(bookmark).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {  }
                    is Resource.Success -> fetchBookmarks()
                    is Resource.Error -> _uiState.value = BookmarksUiState.Error(resource.error ?: "Unknown error")
                }
            }
        }
    }
}