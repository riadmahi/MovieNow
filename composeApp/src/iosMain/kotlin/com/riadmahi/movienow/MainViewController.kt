package com.riadmahi.movienow

import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.runtime.remember
import com.riadmahi.movienow.data.getMovieNowDatabase

fun MainViewController() = ComposeUIViewController {
    val movieNowDatabase = remember {  getMovieNowDatabase() }
    App(movieNowDatabase)
}