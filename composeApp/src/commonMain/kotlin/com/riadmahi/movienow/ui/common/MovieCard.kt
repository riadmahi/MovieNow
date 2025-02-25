package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.Movie
import com.riadmahi.movienow.utils.bounceClick
import com.riadmahi.movienow.utils.shimmerBrush

@Composable
fun MovieCard(
    movie: Movie,
    showTitle: Boolean = true,
    cornerRadius: Dp = 8.dp,
    cardSize: CardSize = CardSize.Medium,
    textAlign: TextAlign = TextAlign.Center,
    onClick: () -> Unit = { }
) {

    Column(
        modifier = Modifier.width(cardSize.width)
            .clip(RoundedCornerShape(cornerRadius))
            .bounceClick()
            .clickable {  onClick() },
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Card(
            modifier = Modifier.size(cardSize.width, cardSize.height),
            shape = RoundedCornerShape(cornerRadius)
        ) {
            AsyncTmdbImage(
                modifier = Modifier.fillMaxSize(),
                posterPath = movie.posterPath
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
        }
    }
}

sealed class CardSize(val width: Dp, val height: Dp, val fontSize: TextUnit) {
    data object Small : CardSize(width = 120.dp, height = 180.dp, fontSize = 14.sp)
    data object Medium : CardSize(width = 180.dp, height = 270.dp, fontSize = 18.sp)
    data object Large : CardSize(width = 240.dp, height = 360.dp, fontSize = 20.sp)
}

