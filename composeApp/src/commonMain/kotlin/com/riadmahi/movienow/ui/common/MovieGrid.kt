package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

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
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.fillMaxWidth().height(25.dp).zIndex(1f).align(Alignment.TopCenter),
                    onDraw = {
                        drawRect(
                            Brush.verticalGradient(listOf(Color.Black, Color.Transparent))
                        )
                    }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 25.dp)
                ) {
                    items(movieListState.movies) { movie ->
                        MovieCard(movie = movie)
                    }
                }


                Canvas(
                    modifier = Modifier.fillMaxWidth().height(25.dp).align(Alignment.BottomCenter),
                    onDraw = {
                        drawRect(
                            Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                        )
                    }
                )
            }

        }
    }
}
