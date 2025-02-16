package com.riadmahi.movienow

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.riadmahi.movienow.ui.common.MovieNowBottomNavigationBar
import com.riadmahi.movienow.ui.main.explore.ExploreScreen
import com.riadmahi.movienow.ui.main.explore.ProfileScreen
import com.riadmahi.movienow.ui.main.explore.SearchScreen
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.ic_explore
import movienow.composeapp.generated.resources.ic_profile
import movienow.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieNowNavHost(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = { MovieNowBottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieNowBottomNavigation.Explore.route,
            modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
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
                ExploreScreen()
            }

            composable(route = MovieNowBottomNavigation.Search.route) {
                SearchScreen()
            }

            composable(route = MovieNowBottomNavigation.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

private val NavController.shouldShowBottomBar
    get() = when (this.currentBackStackEntry?.destination?.route) {
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
