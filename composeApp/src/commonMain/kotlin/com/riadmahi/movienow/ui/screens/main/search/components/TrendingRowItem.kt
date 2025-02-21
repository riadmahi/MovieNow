package com.riadmahi.movienow.ui.screens.main.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riadmahi.movienow.data.model.Movie
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_link_external
import org.jetbrains.compose.resources.painterResource

@Composable
fun TrendingRowItem(movie: Movie, onMovieClicked: () -> Unit = { }) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClicked() }
            .padding(horizontal = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(55.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_link_external),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFFCA8EB0))
            )
            Text(
                text = movie.title,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp),
            color = Color(0xFF3D102A),
            thickness = 0.5.dp
        )
    }
}