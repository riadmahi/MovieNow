package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.riadmahi.movienow.utils.shimmerBrush

@Composable
fun AsyncTmdbImage(
    modifier: Modifier = Modifier,
    posterPath: String?
) {
    var showShimmer by remember { mutableStateOf(true) }

    AsyncImage(
        modifier = modifier
            .background(
                shimmerBrush(targetValue = 1300f, showShimmer = showShimmer)
            ),
        model = "https://image.tmdb.org/t/p/original/${posterPath}",
        contentDescription = "Thumbnail",
        contentScale = ContentScale.Crop,
        onSuccess = { showShimmer = false }
    )
}