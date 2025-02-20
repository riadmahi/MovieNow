package com.riadmahi.movienow.ui.main.movieDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MovieDetailsDescription(
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text("Synopsis", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Left
        )
    }
}
