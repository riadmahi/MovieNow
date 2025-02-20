package com.riadmahi.movienow.ui.main.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.data.model.MovieDetails
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            movieRepository.getMovie(movieId).collect { movieDetailsResource ->
                 val movieDetailsState =when(movieDetailsResource) {
                    Resource.Loading -> MovieDetailsUiState.Loading
                    is Resource.Error -> MovieDetailsUiState.Error(movieDetailsResource.error ?: "Unknow error")
                    is Resource.Success<MovieDetails> -> MovieDetailsUiState.Success(movieDetailsResource.data)
                }
                _uiState.value = movieDetailsState
            }
        }
    }
}
