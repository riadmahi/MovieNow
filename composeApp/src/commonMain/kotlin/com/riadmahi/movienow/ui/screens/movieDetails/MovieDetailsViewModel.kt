package com.riadmahi.movienow.ui.main.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.data.model.MoviePreview
import com.riadmahi.movienow.ui.screens.movieDetails.BookmarkButtonState
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: Int
) : ViewModel() {

    private var _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState: StateFlow<MovieDetailsUiState> = _uiState

    private val _bookmarksList = MutableStateFlow<List<MoviePreview>>(emptyList())
    val bookmarkButtonState: StateFlow<BookmarkButtonState> = _bookmarksList
        .map { bookmarks ->
            if (bookmarks.any { it.id == movieId }) BookmarkButtonState.Delete
            else BookmarkButtonState.Add
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BookmarkButtonState.Add)

    init {
        fetchMovie()
        fetchBookmarkLists()
    }

    private fun fetchMovie() {
        viewModelScope.launch {
            combine(
                movieRepository.getMovie(movieId),
                movieRepository.getMovieWatchProviders(movieId),
                movieRepository.getMovieCredits(movieId)
            ) { movieResource, watchProvidersResource, movieCreditsResource ->
                when (movieResource) {
                    is Resource.Loading -> MovieDetailsUiState.Loading
                    is Resource.Error -> MovieDetailsUiState.Error(movieResource.error ?: "Unknown error")
                    is Resource.Success -> {
                        val providers = if (watchProvidersResource is Resource.Success)
                            watchProvidersResource.data
                        else null

                        val credits = if (movieCreditsResource is Resource.Success)
                            movieCreditsResource.data
                        else null

                        MovieDetailsUiState.Content(
                            movieResource.data,
                            providers?.results?.get("FR")?.flatRate,
                            credits?.cast
                        )
                    }
                }
            }.collect { uiState ->
                _uiState.value = uiState
            }
        }
    }

    private fun fetchBookmarkLists() {
        viewModelScope.launch {
            movieRepository.fetchBookmarks().collect { bookmarkList ->
                print("bookmark list = $bookmarkList")
                _bookmarksList.value = bookmarkList
            }
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch(Dispatchers.Default) {
            val currentState = uiState.value
            if (currentState is MovieDetailsUiState.Content) {
                val movieDetails = currentState.movieDetails
                val moviePreview = MoviePreview(
                    id = movieId,
                    title = movieDetails.title,
                    posterPath = movieDetails.posterPath
                )
                if (_bookmarksList.value.any { it.id == movieId }) {
                    movieRepository.deleteMovieFromBookmark(moviePreview).launchIn(CoroutineScope(Dispatchers.IO))
                } else {
                    movieRepository.addMovieToBookmark(moviePreview).launchIn(CoroutineScope(Dispatchers.IO))
                }
            }
        }
    }


}
