package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieGrid(movieListState: MovieListState) {
    when (movieListState) {
        is MovieListState.Error -> {
            Text(text = "Erreur : ${movieListState.errorMessage}")
        }
        MovieListState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        is MovieListState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(movieListState.movies) { movie ->
                    MovieCard(movie = movie)
                }
            }
        }
    }
}
