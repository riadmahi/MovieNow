package com.riadmahi.movienow.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil3.compose.AsyncImage
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 * Extension function to compute the current offset for a given page.
 * This value is the difference between the current page and the target page,
 * plus the current offset fraction, and is used to animate transformations.
 */
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@Composable
fun Carousel() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Replace these with your actual list of image URLs.
        val sliderList = listOf(
            "https://images.unsplash.com/photo-1739536176048-caa7190dba66?q=80&w=1974&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1739911636351-d1a19684de06?q=80&w=1976&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1593642532400-2682810df593?q=80&w=1974&auto=format&fit=crop"
        )
        // Create and remember the PagerState with the number of pages based on sliderList.
        val pagerState = rememberPagerState(pageCount = { sliderList.size })
        Box(
            modifier = Modifier.fillMaxWidth().height(400.dp).clip(
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
                        model = sliderList[currentPage],
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(50.dp),
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
                                    startY = 0.5f
                                )
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxWidth().height(500.dp),
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                pageSpacing = 100.dp
            ) { page ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier.size(width = 220.dp, height = 320.dp).graphicsLayer {
                                val scale = lerp(0.8f, 1f, 1f - pageOffset.absoluteValue)
                                scaleX = scale
                                scaleY = scale
                                alpha = lerp(0.6f, 1f, 1f - pageOffset.absoluteValue)
                                rotationX = lerp(10f, 0f, 1f - pageOffset.absoluteValue)
                                translationY = lerp(60f, 0f, 1f - pageOffset.absoluteValue)
                                rotationZ = lerp(15f, 0f, 1f - pageOffset.absoluteValue)
                            },
                            backgroundColor = Color.Transparent,
                            shape = RoundedCornerShape(12.dp),
                            elevation = 0.dp
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = sliderList[page],
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 6.dp).graphicsLayer {
                                val scale = lerp(0.5f, 1f, 1f - pageOffset.absoluteValue)
                                scaleX = scale
                                scaleY = scale
                                alpha = lerp(0.6f, 1f, 1f - pageOffset.absoluteValue)
                                rotationX = lerp(25f, 0f, 1f - pageOffset.absoluteValue)
                                translationY = lerp(70f, 0f, 1f - pageOffset.absoluteValue)
                            }
                        ) {
                            Text(
                                text = "Titre",
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onSurface,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "2024",
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}