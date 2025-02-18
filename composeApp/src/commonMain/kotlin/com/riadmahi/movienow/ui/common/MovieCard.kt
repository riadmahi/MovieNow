package com.riadmahi.movienow.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.riadmahi.movienow.data.model.Movie
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    showDescription: Boolean = false,
    textAlign: TextAlign = TextAlign.Center
) {

}


@Composable
@Preview
fun MovieCardPreview() = MovieCard()
