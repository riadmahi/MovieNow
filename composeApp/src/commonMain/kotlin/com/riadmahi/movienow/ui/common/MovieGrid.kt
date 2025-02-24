package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.riadmahi.movienow.data.model.Movie
import kotlinx.coroutines.flow.distinctUntilChanged
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.search_empty_search
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieGrid(
    movieListState: MovieListState,
    onMovieClick: (Movie) -> Unit = { },
    onScroll: () -> Unit = { }
) {
    when (movieListState) {
        is MovieListState.Error -> {
            ErrorMessage(
                title = stringResource(Res.string.search_empty_search),
                description = null
            )
        }

        MovieListState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        is MovieListState.Success -> {
            if (movieListState.movies.isEmpty()) {
                ErrorMessage(
                    title = stringResource(Res.string.search_empty_search)
                )
            } else {
                val lazyGridState = rememberLazyGridState()

                LaunchedEffect(lazyGridState) {
                    snapshotFlow { lazyGridState.isScrollInProgress }
                        .distinctUntilChanged()
                        .collect { isScrolling ->
                            if (isScrolling) {
                                onScroll()
                            }
                        }
                }

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
                        state = lazyGridState,
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(vertical = 25.dp)
                    ) {
                        items(movieListState.movies) { movie ->
                            MovieCard(movie = movie, onClick = { onMovieClick(movie) })
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
}
