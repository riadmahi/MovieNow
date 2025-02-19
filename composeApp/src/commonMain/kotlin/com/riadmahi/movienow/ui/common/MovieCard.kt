package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.Movie
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Composable
fun MovieCard(
    movie: Movie,
    showTitle: Boolean = true,
    showDescription: Boolean = true,
    cornerRadius: Dp = 8.dp,
    cardSize: CardSize = CardSize.Medium,
    textAlign: TextAlign = TextAlign.Center
) {
    Column(
        modifier = Modifier.width(cardSize.width),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
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
        Column {
            if(showTitle) {
                Text(
                    text = movie.title,
                    textAlign = textAlign,
                    fontSize = cardSize.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if(showDescription) {
                val dateFormat = LocalDate.parse(movie.releaseDate)
                val dateText = "${dateFormat.month}  ${dateFormat.year}"
                Text(
                    text = dateText,
                    textAlign = textAlign,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

sealed class CardSize(val width: Dp, val height: Dp, val fontSize: TextUnit) {
    data object Small : CardSize(width = 120.dp, height = 180.dp, fontSize = 14.sp)
    data object Medium : CardSize(width = 180.dp, height = 270.dp, fontSize = 18.sp)
    data object Large : CardSize(width = 240.dp, height = 360.dp, fontSize = 20.sp)
}

