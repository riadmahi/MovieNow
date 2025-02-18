package com.riadmahi.movienow.ui.main.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<ExploreUiState>(ExploreUiState.Loading)
    val uiState: StateFlow<ExploreUiState> = _uiState

    init {
        fetchTopRatedMovie()
    }

    private fun fetchTopRatedMovie() {
        viewModelScope.launch {
            movieRepository.getTopRatedMovieList().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _uiState.value = ExploreUiState.Content(
                            popularMovies = ExploreUiState.PopularMovieState.Error(
                                resource.error ?: "Unknown error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _uiState.value =
                            ExploreUiState.Content(popularMovies = ExploreUiState.PopularMovieState.Loading)
                    }

                    is Resource.Success -> {
                        _uiState.value = ExploreUiState.Content(
                            popularMovies = ExploreUiState.PopularMovieState.Success(movies = resource.data.results)
                        )
                    }
                }
            }
        }
    }
}