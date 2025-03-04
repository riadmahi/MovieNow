package com.riadmahi.movienow

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.riadmahi.movienow.data.MovieRepository
import com.riadmahi.movienow.ui.common.MovieNowBottomNavigationBar
import com.riadmahi.movienow.ui.main.explore.*
import com.riadmahi.movienow.ui.main.movieDetails.MovieDetailsScreen
import com.riadmahi.movienow.ui.main.movieDetails.MovieDetailsViewModel
import com.riadmahi.movienow.ui.main.search.SearchViewModel
import com.riadmahi.movienow.ui.screens.main.bookmarks.BookmarksViewModel
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_explore
import movienow.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun MovieNowNavHost(navController: NavHostController, movieRepository: MovieRepository) {
    Scaffold(
        bottomBar = { if(navController.shouldShowBottomBar) MovieNowBottomNavigationBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieNowBottomNavigation.Explore.route, //for debbuging details view"${MovieNowDestination.MovieDetails.route}/1118031"
            modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .safeDrawingPadding()
        ) {
            composable(route = MovieNowBottomNavigation.Explore.route) {
                val viewModel = viewModel<ExploreViewModel> { ExploreViewModel(movieRepository) }
                ExploreScreen(
                    viewModel = viewModel,
                    onNavigateToMovieDetails = { movie ->
                        navController.navigate("${MovieNowDestination.MovieDetails.route}/${movie.id}")
                    }
                )
            }

            composable(route = MovieNowBottomNavigation.Search.route) {
                val viewModel = viewModel<SearchViewModel> {
                    SearchViewModel(movieRepository = movieRepository)
                }
                SearchScreen(
                    viewModel = viewModel,
                    onNavigateToMovieDetails = { movie ->
                        navController.navigate("${MovieNowDestination.MovieDetails.route}/${movie.id}")
                    }
                )
            }

            composable(route = MovieNowBottomNavigation.Bookmark.route) {
                val viewModel = viewModel<BookmarksViewModel> {
                    BookmarksViewModel(movieRepository = movieRepository)
                }
                BookmarksScreen(
                    viewModel = viewModel,
                    onNavigateToMovieDetails = { movieId ->
                        navController.navigate("${MovieNowDestination.MovieDetails.route}/${movieId}")
                    }
                )
            }

            composable(
                route = "${MovieNowDestination.MovieDetails.route}/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("movieId") ?: 0
                val viewModel = viewModel<MovieDetailsViewModel> {
                    MovieDetailsViewModel(movieRepository = movieRepository, movieId = id)
                }
                MovieDetailsScreen(
                    viewModel = viewModel,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}

private val NavController.shouldShowBottomBar
    @Composable
    get() = when (this.currentBackStackEntryAsState().value?.destination?.route) {
        MovieNowBottomNavigation.Explore.route,
        MovieNowBottomNavigation.Search.route,
        MovieNowBottomNavigation.Bookmark.route,
            -> true

        else -> false
    }

sealed class MovieNowBottomNavigation(val route: String, val title: StringResource, val icon: DrawableResource) {
    data object Explore : MovieNowBottomNavigation(route = "explore", title = Res.string.explore, icon = Res.drawable.ic_explore)
    data object Search : MovieNowBottomNavigation(route = "search", title =  Res.string.search, icon = Res.drawable.ic_search)
    data object Bookmark : MovieNowBottomNavigation(route = "bookmarks", title =  Res.string.bookmarks, icon = Res.drawable.ic_bookmark)
}


sealed class MovieNowDestination(val route: String) {
    data object MovieDetails : MovieNowDestination(route = "movie")
}