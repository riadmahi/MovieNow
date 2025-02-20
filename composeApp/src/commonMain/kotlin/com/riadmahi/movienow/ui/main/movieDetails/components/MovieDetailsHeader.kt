package com.riadmahi.movienow.ui.main.movieDetails.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.riadmahi.movienow.ui.common.CardSize
import movienow.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import movienow.composeapp.generated.resources.ic_arrow_left

@Composable
fun MovieDetailsHeader(
    thumbnail: String,
    title: String,
    year: String,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(460.dp)
    ) {

        Card(
            shape = RoundedCornerShape(
                topEnd = 12.dp,
                topStart = 12.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(50.dp),
                model = thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop)
        }

        Canvas(
            modifier = Modifier.fillMaxWidth().height(200.dp).align(Alignment.BottomCenter),
            onDraw = {
                drawRect(
                    Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                )
            }
        )
        Column(
            modifier = Modifier
                .width(CardSize.Large.width)
                .align(Alignment.BottomCenter)
                .offset(y = 50.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(CardSize.Large.width, CardSize.Large.height),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = CardSize.Large.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "($year)",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Image(
            painterResource(Res.drawable.ic_arrow_left),
            contentDescription = Res.drawable.ic_arrow_left.toString(),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp)
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(50.dp))
                .clickable { navigateBack() }

        )
    }
}