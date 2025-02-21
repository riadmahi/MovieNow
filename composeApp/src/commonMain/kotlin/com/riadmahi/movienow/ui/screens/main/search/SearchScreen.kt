package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.main.search.SearchUiState
import com.riadmahi.movienow.ui.main.search.SearchViewModel
import com.riadmahi.movienow.ui.screens.main.search.components.TrendingSection

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextField(
            searchText,
            onValueChange = { searchText = it },
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
        )
        when (uiState) {
            is SearchUiState.Error -> {
                Text(text = "Error: ${(uiState as SearchUiState.Error).errorMessage}")
            }
            SearchUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
            is SearchUiState.Success -> {
                // TODO: Show search results
            }
            is SearchUiState.Trending -> {
                TrendingSection(trendingState = (uiState as SearchUiState.Trending).tredingMovieListState)
            }
        }
    }
}
