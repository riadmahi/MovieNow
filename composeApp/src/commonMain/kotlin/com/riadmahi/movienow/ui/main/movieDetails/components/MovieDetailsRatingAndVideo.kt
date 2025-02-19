package com.riadmahi.movienow.ui.main.movieDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailsRatingAndVideo() {
    val numberRating = 12
    val score = 6.7F / 10
    val textScore = "67%"
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 70.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingScore(
            textScore= textScore,
            score= score,
            numberRating= numberRating
        )
    }
}

@Composable
fun RatingScore(textScore: String, score: Float, numberRating: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), progress = score, backgroundColor = MaterialTheme.colors.secondary)
            Text(textScore, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }
        Column{
            Text("Rating note", fontWeight = FontWeight.Medium, fontSize = 15.sp)
            Text("$numberRating votes", fontWeight = FontWeight.Normal, fontSize = 13.sp, color = Color.Gray)
        }
    }
}