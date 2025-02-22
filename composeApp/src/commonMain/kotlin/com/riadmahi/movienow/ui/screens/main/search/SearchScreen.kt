package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.ui.common.MovieGrid
import com.riadmahi.movienow.ui.main.search.SearchUiState
import com.riadmahi.movienow.ui.main.search.SearchViewModel
import com.riadmahi.movienow.ui.screens.main.search.components.TrendingSection

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateToMovieDetails: (Movie) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // Unfocus text field when clicking outside
                })
            },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            searchText,
            onValueChange = { newValue ->
                searchText = newValue
                viewModel.searchMovies(newValue.text)
            },
            placeholder = { Text("Search a movie") },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFF1A0B13),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = Color(0xFFCA8EB0),
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            maxLines = 1
        )
        when (uiState) {
            is SearchUiState.Error -> {
                Text(text = "Error: ${(uiState as SearchUiState.Error).errorMessage}")
            }
            SearchUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }
            is SearchUiState.Success -> {
                MovieGrid(
                    movieListState = (uiState as SearchUiState.Success).movieFoundListState,
                    onMovieClick = { onNavigateToMovieDetails(it) }
                )
            }
            is SearchUiState.Trending -> {
                TrendingSection(
                    trendingState = (uiState as SearchUiState.Trending).tredingMovieListState,
                    onMovieClicked = {
                        searchText = TextFieldValue(it.title)
                        viewModel.searchMovies(it.title)
                    }
                )
            }
        }
    }
}
