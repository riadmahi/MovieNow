package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riadmahi.movienow.ui.common.MovieNowButton
import com.riadmahi.movienow.ui.screens.main.bookmarks.components.CreateBookmarkDialog
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.bookmarks_list_add_list
import movienow.composeapp.generated.resources.bookmarks_list_empty
import movienow.composeapp.generated.resources.illustration_bookmarks
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarksScreen() {

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.illustration_bookmarks),
            contentDescription = Res.drawable.illustration_bookmarks.toString(),
            modifier = Modifier.size(250.dp)
        )

        Column(
            modifier = Modifier.padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
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
            MovieNowButton(text = stringResource(Res.string.button_create)) {
                showDialog = true
            }
        }
    }
    if (showDialog) {
        CreateBookmarkDialog(
            onDismiss = { showDialog = false },
            onCreate = { listName ->
                showDialog = false
            }
        )
    }
}

