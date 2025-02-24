package com.riadmahi.movienow.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil3.compose.AsyncImage
import com.riadmahi.movienow.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.movie_section_upcoming
import org.jetbrains.compose.resources.stringResource
import kotlin.math.absoluteValue

/**
 * Extension function to compute the current offset for a given page.
 * This value is the difference between the current page and the target page,
 * plus the current offset fraction, and is used to animate transformations.
 */
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@Composable
fun Carousel(
    state: MovieListState,
    onMovieClick: (Movie) -> Unit = { }
) {

    when (state) {
        is MovieListState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        is MovieListState.Error -> {
            DefaultErrorScreen()
        }

        is MovieListState.Success -> {
            val movies = state.movies
            val pagerState = rememberPagerState(pageCount = { movies.size })
            LaunchedEffect(pagerState) {
                if (pagerState.currentPageOffsetFraction != 0f) {
                    pagerState.scrollToPage(pagerState.currentPage)
                }

                while (true) {
                    delay(5000L)
                    if (pagerState.isScrollInProgress) continue
                    val nextPage = (pagerState.currentPage + 1) % movies.size
                    pagerState.animateScrollToPage(
                        nextPage,
                        animationSpec = tween(durationMillis = 1400)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topEnd = 12.dp,
                            topStart = 12.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            ) {

                Crossfade(targetState = pagerState.currentPage) { currentPage ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/original/${movies[currentPage].backdropPath}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .align(Alignment.BottomCenter),
                            onDraw = {
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 0f,
                                        endY = size.height
                                    )
                                )
                            }
                        )
                    }
                }
                // Horizontal pager displaying movie cards.
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    state = pagerState,
                    verticalAlignment = Alignment.CenterVertically,
                    pageSpacing = 100.dp
                ) { page ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(bottom = 12.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(
                                modifier = Modifier
                                    .size(width = 220.dp, height = 320.dp)
                                    .graphicsLayer {
                                        val scale = lerp(0.8f, 1f, 1f - pageOffset.absoluteValue)
                                        scaleX = scale
                                        scaleY = scale
                                        alpha = lerp(0.6f, 1f, 1f - pageOffset.absoluteValue)
                                        rotationX = lerp(10f, 0f, 1f - pageOffset.absoluteValue)
                                        translationY = lerp(60f, 0f, 1f - pageOffset.absoluteValue)
                                        rotationZ = lerp(15f, 0f, 1f - pageOffset.absoluteValue)
                                    }
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { onMovieClick(movies[page]) }
                                ,
                                backgroundColor = Color.Transparent,
                                shape = RoundedCornerShape(12.dp),
                                elevation = 0.dp
                            ) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    model = "https://image.tmdb.org/t/p/original/${movies[page].posterPath}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            // Text info (title and release year)
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .graphicsLayer {
                                        val scale = lerp(0.5f, 1f, 1f - pageOffset.absoluteValue)
                                        scaleX = scale
                                        scaleY = scale
                                        alpha = lerp(0f, 1f, 1f - pageOffset.absoluteValue)
                                        rotationX = lerp(25f, 0f, 1f - pageOffset.absoluteValue)
                                        translationY = lerp(70f, 0f, 1f - pageOffset.absoluteValue)
                                    }
                            ) {
                                Text(
                                    text = movies[page].title,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colors.onSurface,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "${movies[page].releaseDate.let { LocalDate.parse(it).year }}",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Text(
                    stringResource(Res.string.movie_section_upcoming),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 24.dp)
                )
            }
        }
    }
}