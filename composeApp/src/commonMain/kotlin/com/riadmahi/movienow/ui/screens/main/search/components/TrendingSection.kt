package com.riadmahi.movienow.ui.screens.main.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.riadmahi.movienow.ui.common.MovieListState


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