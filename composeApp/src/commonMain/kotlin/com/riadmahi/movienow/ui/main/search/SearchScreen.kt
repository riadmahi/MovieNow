package com.riadmahi.movienow.ui.main.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.riadmahi.movienow.ui.common.MovieListState
import com.riadmahi.movienow.ui.main.search.SearchUiState
import com.riadmahi.movienow.ui.main.search.SearchViewModel
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_link_external
import movienow.composeapp.generated.resources.ic_trend_up
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

        when(uiState) {
            is SearchUiState.Error -> { }
            SearchUiState.Loading -> { }
            is SearchUiState.Success -> { }
            is SearchUiState.Trending -> {
                when((uiState as SearchUiState.Trending).tredingMovieListState) {
                    is MovieListState.Error -> {
                        Text("Error")
                    }
                    MovieListState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                    is MovieListState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF1A0B13))
                        ) {
                            stickyHeader {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                        .background(Color(0xFF1A0B13))
                                        .padding(top = 6.dp)
                                    ,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Image(
                                        painter = painterResource(Res.drawable.ic_trend_up),
                                        contentDescription = Res.drawable.ic_trend_up.toString(),
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = ColorFilter.tint(Color.Gray)
                                    )
                                    Text(
                                        "TRENDING",
                                        fontSize = 13.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                            items((((uiState as SearchUiState.Trending).tredingMovieListState) as MovieListState.Success).movies){ movie ->
                                Column(Modifier.fillMaxWidth()
                                    .padding(horizontal = 12.dp)) {
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
                                            movie.title,
                                            fontSize = 16.sp
                                        )
                                    }
                                        Divider(
                                            Modifier
                                                .fillMaxSize()
                                                .padding(start = 12.dp),
                                            color = Color(0xFFCA8EB0),
                                            thickness = (0.5).dp
                                        )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

