package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.riadmahi.movienow.data.model.Movie
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_arrow_right
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieRow(
    title: String,
    movies: List<Movie>,
    cardSize: CardSize = CardSize.Medium,
    showTitle: Boolean = false,
    onMovieClick: (Movie) -> Unit = { }
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "MORE",
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 0.sp
                )
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = Res.drawable.ic_arrow_right.toString(),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieCard(
                    movie = movie,
                    cardSize = cardSize,
                    showTitle = showTitle,
                    onClick = { onMovieClick(movie) }
                )
            }
        }
    }
}