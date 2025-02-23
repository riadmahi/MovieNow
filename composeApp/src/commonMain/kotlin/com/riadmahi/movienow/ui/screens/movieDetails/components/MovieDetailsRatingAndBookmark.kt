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
import com.riadmahi.movienow.ui.common.MovieNowButton
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.button_add
import movienow.composeapp.generated.resources.movie_details_rating_note
import movienow.composeapp.generated.resources.movie_details_votes
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieDetailsRatingAndBookmark(
    numberRating: Int,
    score: Float,
    textScore: String
) {
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

        MovieNowButton(text = stringResource(Res.string.button_add))
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
            Text(stringResource(Res.string.movie_details_rating_note), fontWeight = FontWeight.Medium, fontSize = 15.sp)
            Text(
                "$numberRating ${stringResource(Res.string.movie_details_votes)}",
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp, color = Color.Gray
            )
        }
    }
}