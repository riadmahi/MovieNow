package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riadmahi.movienow.ui.common.MovieNowButton
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.illustration_bookmarks
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookmarksScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.illustration_bookmarks),
            contentDescription = "Empty list illustration",
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
                    text = "Your bookmarks list is empty!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Start by adding your first one.",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
            MovieNowButton(text = "Create") {

            }
        }
    }
}