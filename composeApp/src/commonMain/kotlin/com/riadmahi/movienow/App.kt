package com.riadmahi.movienow

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.riadmahi.movienow.ui.theme.MovieNowTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MovieNowTheme {
        MovieNowNavHost(navController)
    }
}
