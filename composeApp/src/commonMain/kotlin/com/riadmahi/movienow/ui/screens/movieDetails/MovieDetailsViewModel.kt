package com.riadmahi.movienow.ui.main.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: Int
) : ViewModel() {

    private var _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState: StateFlow<MovieDetailsUiState> = _uiState

    init {
        fetchMovie()
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
                        
                        MovieDetailsUiState.Content(movieResource.data, providers?.results?.get("FR")?.flatRate, credits?.cast)
                    }
                }
            }.collect { uiState ->
                _uiState.value = uiState
            }
        }
    }
}
