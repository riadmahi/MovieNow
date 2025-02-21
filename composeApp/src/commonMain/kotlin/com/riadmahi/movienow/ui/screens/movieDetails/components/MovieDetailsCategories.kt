package com.riadmahi.movienow.ui.main.movieDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riadmahi.movienow.data.model.Genre

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsCategories(categories : List<Genre>) {
    FlowRow(
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
        .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        categories.forEach {
            Text(
                text = it.name,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline,
                color = Color.Gray
            )
        }
    }
}