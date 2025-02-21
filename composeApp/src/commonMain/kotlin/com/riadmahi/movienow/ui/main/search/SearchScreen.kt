package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.ui.common.MovieListState
import com.riadmahi.movienow.ui.main.search.SearchUiState
import com.riadmahi.movienow.ui.main.search.SearchViewModel
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_link_external
import movienow.composeapp.generated.resources.ic_trend_up
import org.jetbrains.compose.resources.painterResource

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingSection(trendingState: MovieListState) {
    when (trendingState) {
        is MovieListState.Error -> {
            Text(text = "Error")
        }
        MovieListState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }

        is MovieListState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1A0B13))
            ) {
                stickyHeader { TrendingHeader() }
                items(trendingState.movies) { movie ->
                    TrendingRowItem(movie = movie)
                }
            }
        }
    }
}

@Composable
fun TrendingHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(Color(0xFF1A0B13))
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_trend_up),
            contentDescription = Res.drawable.ic_trend_up.toString(),
            modifier = Modifier.size(16.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Text(
            text = "TRENDING",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun TrendingRowItem(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(55.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_link_external),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFFCA8EB0))
            )
            Text(
                text = movie.title,
                fontSize = 16.sp
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            color = Color(0xFFCA8EB0),
            thickness = 0.5.dp
        )
    }
}