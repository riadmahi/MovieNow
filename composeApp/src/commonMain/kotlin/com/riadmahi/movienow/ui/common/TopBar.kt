package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_arrow_left
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieNowTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Image(
            painterResource(Res.drawable.ic_arrow_left),
            contentDescription = Res.drawable.ic_arrow_left.toString(),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(50.dp))
                .clickable { navigateBack() }
        )
    }
}