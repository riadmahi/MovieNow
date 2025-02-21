package com.riadmahi.movienow.ui.screens.main.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_trend_up
import org.jetbrains.compose.resources.painterResource


@Composable
fun TrendingHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(Color(0xFF1A0B13))
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_trend_up),
            contentDescription = Res.drawable.ic_trend_up.toString(),
            modifier = Modifier.size(16.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Text(
            text = "TRENDING",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}
