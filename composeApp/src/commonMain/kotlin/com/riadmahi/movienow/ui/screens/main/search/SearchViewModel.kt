package com.riadmahi.movienow.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.ui.common.MovieListState
import com.riadmahi.movienow.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce

class SearchViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState

    private var searchJob: Job? = null

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

    fun searchMovies(query: String) {
        searchJob?.cancel()
        if (query.isBlank()) {
            fetchTrendingMovies()
            return
        }
        searchJob = viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            delay(900)
            movieRepository.getSearchMovieList(query).collect { searchResource ->
                val searchState = when (searchResource) {
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = searchResource.data.results)
                    is Resource.Error -> MovieListState.Error(searchResource.error ?: "Erreur inconnue")
                }
                _uiState.value = SearchUiState.Success(movieFoundListState = searchState)
            }
        }
    }
}