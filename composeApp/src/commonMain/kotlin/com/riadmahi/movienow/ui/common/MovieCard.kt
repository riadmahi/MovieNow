package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    showDescription: Boolean = false,
    cornerRadius: Dp = 8.dp,
    cardSize: CardSize = CardSize.Medium,
    textAlign: TextAlign = TextAlign.Center
) {
    Column {
        Card(
            modifier = Modifier.size(cardSize.width, cardSize.height),
            shape = RoundedCornerShape(cornerRadius)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = "https://image.tmdb.org/t/p/original/${movie.posterPath}",
                contentDescription = "Movie thumbnail",
                contentScale = ContentScale.Crop
            )
        }
        if(showDescription) {
            Text(
                text = movie.title,
                textAlign = textAlign,
                fontSize = cardSize.fontSize,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

sealed class CardSize(val width: Dp, val height: Dp, val fontSize: TextUnit) {
    data object Small : CardSize(width = 120.dp, height = 180.dp, fontSize = 16.sp)
    data object Medium : CardSize(width = 180.dp, height = 270.dp, fontSize = 20.sp)
    data object Large : CardSize(width = 240.dp, height = 360.dp, fontSize = 24.sp)
}

/*
@Preview
@Composable
fun MovieCardPreview() = MovieCard()
*/