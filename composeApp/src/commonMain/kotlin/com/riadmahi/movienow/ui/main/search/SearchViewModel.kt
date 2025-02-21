package com.riadmahi.movienow.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.ui.common.MovieListState
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
        fetchTrendingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            movieRepository.getTrendingMovieList().collect { trendingResource ->
                val trendingState = when (trendingResource) {
                    is Resource.Loading ->MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = trendingResource.data.results)
                    is Resource.Error -> MovieListState.Error(trendingResource.error ?: "Unknown error")
                }
                _uiState.value = SearchUiState.Trending(tredingMovieListState = trendingState)
            }
        }
    }
}