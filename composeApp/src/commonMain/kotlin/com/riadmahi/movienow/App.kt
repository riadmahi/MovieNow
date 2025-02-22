package com.riadmahi.movienow

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.riadmahi.movienow.data.LocalDB
import com.riadmahi.movienow.data.MovieApi
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.data.database.MovieNowDatabase
import com.riadmahi.movienow.ui.theme.MovieNowTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    movieNowDatabase: MovieNowDatabase
) {
    val navController = rememberNavController()
    val movieRepository = MovieRepository(api = MovieApi(), localDB = LocalDB(movieNowDatabase))
    MovieNowTheme {
        MovieNowNavHost(navController, movieRepository)
    }
}
