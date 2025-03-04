package com.riadmahi.movienow.ui.main.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.ui.common.MovieListState
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

    fun fetchMovies() {
        _uiState.value = ExploreUiState.Loading
        viewModelScope.launch {
            combine(
                movieRepository.getPopularMovieList(),
                movieRepository.getNowPlayingMovieList(),
                movieRepository.getUpcomingMovieList(),
                movieRepository.getTopRatedMovieList()
            ) { popularResource, nowPlayingResource,
                upcomingResource, topRatedResource ->
                val popularMoviesState = when (popularResource) {
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = popularResource.data.results)
                    is Resource.Error -> MovieListState.Error(popularResource.error ?: "Unknown error")
                }

                val nowPlayingMoviesState = when (nowPlayingResource) {
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = nowPlayingResource.data.results)
                    is Resource.Error -> MovieListState.Error(nowPlayingResource.error ?: "Unknown error")
                }

                val upcomingMoviesState = when (upcomingResource) {
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = upcomingResource.data.results)
                    is Resource.Error -> MovieListState.Error(upcomingResource.error ?: "Unknown error")
                }

                val topRatedMoviesState = when (topRatedResource) {
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = topRatedResource.data.results)
                    is Resource.Error -> MovieListState.Error(topRatedResource.error ?: "Unknown error")
                }

                if(popularMoviesState is MovieListState.Error) {
                    ExploreUiState.Error("Internal error")
                } else {
                    ExploreUiState.Content(
                        popularMovies = popularMoviesState,
                        nowPlayingMovies = nowPlayingMoviesState,
                        upcomingMovies = upcomingMoviesState,
                        topRatedMovies = topRatedMoviesState
                    )
                }

            }.collect { combinedContentState ->
                _uiState.value = combinedContentState
            }
        }
    }
}