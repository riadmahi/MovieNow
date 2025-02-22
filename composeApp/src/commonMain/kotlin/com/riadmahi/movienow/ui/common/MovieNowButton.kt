package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riadmahi.movienow.utils.bounceClick

@Composable
fun MovieNowButton(
    text : String,
    onClick: () -> Unit = { }
) {
    Button(
        modifier = Modifier.bounceClick(),
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1A0B13)),
        border = BorderStroke(width = 1.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(30.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}