package com.riadmahi.movienow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.riadmahi.movienow.data.getMovieNowDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieNowDatabase = getMovieNowDatabaseBuilder(context = applicationContext)
        setContent {
            App(movieNowDatabase)
        }
    }
}
