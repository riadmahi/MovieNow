package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ExploreScreen(viewModel: ExploreViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
}