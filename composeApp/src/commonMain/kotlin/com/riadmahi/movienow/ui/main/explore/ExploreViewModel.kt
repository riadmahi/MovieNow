package com.riadmahi.movienow.ui.main.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ExploreViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<ExploreUiState>(ExploreUiState.Loading)
    val uiState: StateFlow<ExploreUiState> = _uiState

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            combine(
                movieRepository.getPopularMovieList(),
                movieRepository.getNowPlayingMovieList()
            ) { popularResource, nowPlayingResource ->
                val popularMoviesState = when (popularResource) {
                    is Resource.Loading -> ExploreUiState.MovieListState.Loading
                    is Resource.Success -> ExploreUiState.MovieListState.Success(movies = popularResource.data.results)
                    is Resource.Error -> ExploreUiState.MovieListState.Error(popularResource.error ?: "Unknown error")
                }

                val nowPlayingMoviesState = when (nowPlayingResource) {
                    is Resource.Loading -> ExploreUiState.MovieListState.Loading
                    is Resource.Success -> ExploreUiState.MovieListState.Success(movies = nowPlayingResource.data.results)
                    is Resource.Error -> ExploreUiState.MovieListState.Error(nowPlayingResource.error ?: "Unknown error")
                }

                ExploreUiState.Content(
                    popularMovies = popularMoviesState,
                    nowPlayingMovies = nowPlayingMoviesState
                )
            }.collect { combinedContentState ->
                _uiState.value = combinedContentState
            }
        }
    }
}