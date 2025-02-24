package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.MovieNowButton
import com.riadmahi.movienow.ui.screens.main.bookmarks.BookmarksUiState
import com.riadmahi.movienow.ui.screens.main.bookmarks.BookmarksViewModel
import com.riadmahi.movienow.ui.screens.main.bookmarks.components.BookmarkMovie
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.bookmarks_list_add_list
import movienow.composeapp.generated.resources.bookmarks_list_empty
import movienow.composeapp.generated.resources.illustration_bookmarks
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarksScreen(viewModel: BookmarksViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is BookmarksUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is BookmarksUiState.Error -> {
                Text(
                    text = "Error: ${(uiState as BookmarksUiState.Error).error}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is BookmarksUiState.Success -> {
                val movies = (uiState as BookmarksUiState.Success).bookmarkList
                if (movies.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.illustration_bookmarks),
                            contentDescription = Res.drawable.illustration_bookmarks.toString(),
                            modifier = Modifier.size(250.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = stringResource(Res.string.bookmarks_list_empty),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = stringResource(Res.string.bookmarks_list_add_list),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = stringResource(Res.string.bookmarks_list),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(movies) { movie ->
                                BookmarkMovie(movie)
                            }
                        }
                    }

                }
            }
        }
    }
}
