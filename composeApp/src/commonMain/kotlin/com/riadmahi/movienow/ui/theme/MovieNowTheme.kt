package com.riadmahi.movienow.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieNowTheme(
    darkTheme: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colors = Colors(
        primary = Color(0xFFFF28A2),
        primaryVariant = Color(0xFFFF28A2),
        secondary = Color(0xFFFF28A2),
        secondaryVariant = Color(0xFFFF28A2),
        background = Color.Black,
        surface = Color.Black,
        error = Color.Red,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        onError = Color.White,
        isLight = false
    )

    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = BrSonomaTypography(),
        shapes = shapes,
        content = content
    )
}
