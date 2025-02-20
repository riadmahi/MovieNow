package com.riadmahi.movienow

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import com.riadmahi.movienow.ui.main.explore.ExploreScreen
import com.riadmahi.movienow.ui.main.explore.ExploreViewModel
import com.riadmahi.movienow.ui.main.explore.ProfileScreen
import com.riadmahi.movienow.ui.main.explore.SearchScreen
import com.riadmahi.movienow.ui.main.movieDetails.MovieDetailsScreen
import com.riadmahi.movienow.ui.main.movieDetails.MovieDetailsViewModel
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_explore
import movienow.composeapp.generated.resources.ic_profile
import movienow.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun MovieNowNavHost(navController: NavHostController, movieRepository: MovieRepository) {
    Scaffold(
        bottomBar = { if(navController.shouldShowBottomBar) MovieNowBottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "${MovieNowDestination.MovieDetails.route}/1118031",
            modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .safeDrawingPadding(),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }) {
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
                SearchScreen()
            }

            composable(route = MovieNowBottomNavigation.Profile.route) {
                ProfileScreen()
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
        MovieNowBottomNavigation.Profile.route,
            -> true

        else -> false
    }

sealed class MovieNowBottomNavigation(val route: String, val title: StringResource, val icon: DrawableResource) {
    data object Explore : MovieNowBottomNavigation(route = "explore", title = Res.string.explore, icon = Res.drawable.ic_explore)
    data object Search : MovieNowBottomNavigation(route = "search", title =  Res.string.search, icon = Res.drawable.ic_search)
    data object Profile : MovieNowBottomNavigation(route = "profile", title =  Res.string.profile, icon = Res.drawable.ic_profile)
}


sealed class MovieNowDestination(val route: String) {
    data object MovieDetails : MovieNowDestination(route = "movie")
}