package com.riadmahi.movienow.ui.main.movieDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsDescription
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsHeader
import com.riadmahi.movienow.ui.main.movieDetails.components.MovieDetailsRatingAndVideo

@Composable
fun MovieDetailsScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            MovieDetailsHeader()
            MovieDetailsRatingAndVideo()
            MovieDetailsDescription()
        }
    }
}
