package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.riadmahi.movienow.MovieNowBottomNavigation
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieNowBottomNavigationBar(navController: NavController) {
    val tabs =
        listOf(MovieNowBottomNavigation.Explore, MovieNowBottomNavigation.Search, MovieNowBottomNavigation.Profile)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    
    BottomNavigation(backgroundColor = Color.White, elevation = 0.dp) {
        tabs.forEach { tab ->
            val isSelected = currentRoute == tab.route
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(tab.icon),
                        contentDescription = tab.icon.toString(),
                        colorFilter = if (isSelected) {
                            ColorFilter.tint(Color.Black)
                        } else {
                            ColorFilter.tint(Color.Gray)
                        }
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Medium,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                },
                onClick = {
                    navigateBottomBar(navController, tab.route)
                },
                selected = isSelected,
            )
        }
    }
}

private fun navigateBottomBar(navController: NavController, destination: String) {
    navController.navigate(destination) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(MovieNowBottomNavigation.Explore.route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
