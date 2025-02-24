package com.riadmahi.movienow.ui.main.movieDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.CastMember
import com.riadmahi.movienow.ui.common.CardSize
import com.riadmahi.movienow.utils.shimmerBrush
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.movie_details_casting
import movienow.composeapp.generated.resources.movie_details_casting_photo_desc
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieDetailsCasting(casting: List<CastMember>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            stringResource(Res.string.movie_details_casting),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(casting) { castMember ->
                CastingCard(castMember)
            }
        }
    }
}

@Composable
fun CastingCard(castMember: CastMember) {
    var showShimmer by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.size(width = CardSize.Small.width, height = 240.dp)
    ) {
        Card(
            modifier = Modifier.size(CardSize.Small.width, CardSize.Small.height),
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer)),
                model = "https://image.tmdb.org/t/p/original/${castMember.profilePath}",
                contentDescription = stringResource(Res.string.movie_details_casting_photo_desc),
                contentScale = ContentScale.Crop,
                onSuccess = { showShimmer = false }
            )
        }
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = castMember.name,
            fontSize = CardSize.Small.fontSize,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 15.sp
        )

        Text(
            castMember.character,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 2,
            lineHeight = 13.sp,
            color = Color.Gray
        )
    }
}