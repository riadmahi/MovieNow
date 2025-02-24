package com.riadmahi.movienow.ui.screens.movieDetails

sealed class BookmarkButtonState {
    data object Add : BookmarkButtonState()
    data object Delete : BookmarkButtonState()
}