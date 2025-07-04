package com.example.hw_3_6month.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hw_3_6month.ui.screens.characters.CharacterDetailScreen
import com.example.hw_3_6month.ui.screens.characters.CharactersScreen
import com.example.hw_3_6month.ui.screens.episodes.EpisodeDetailScreen
import com.example.hw_3_6month.ui.screens.locations.LocationsScreen
import com.example.hw_3_6month.ui.screens.episodes.EpisodesScreen
import com.example.hw_3_6month.ui.screens.locations.LocationDetailScreen
import com.example.hw_3_6month.ui.theme.DarkGray
import com.example.hw_3_6month.ui.theme.Gray

data class BottomNavItem(val screen: Screen, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun app() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //скрытие апп бара для детальных экранов
    val showAppBars = when {
        currentRoute?.startsWith(Screen.CharacterDetail.route) == true ||
                currentRoute?.startsWith(Screen.LocationDetail.route) == true ||
                currentRoute?.startsWith(Screen.EpisodeDetail.route) == true -> false
        else -> true
    }

    Scaffold(
        topBar = {
            if (showAppBars) {
                TopAppBar(
                    title = { Text(text = currentScreenTitle(currentRoute).toString(), color = Color.White) },
                    colors = TopAppBarColors(
                        containerColor = DarkGray,
                        scrolledContainerColor = Color.White,
                        navigationIconContentColor = Color.Black,
                        titleContentColor = Gray,
                        actionIconContentColor = Color.Red
                    )
                )
            }
        },
        bottomBar = {
            if (showAppBars) {
                BottomNavigationBar(navController, currentRoute)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Characters.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Characters.route) {
                CharactersScreen(navController)
            }
            composable(Screen.Locations.route) {
                LocationsScreen(navController = navController)
            }
            composable(Screen.Episodes.route) {
                EpisodesScreen(navController = navController)
            }
            composable(
                route = "character_detail/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
                CharacterDetailScreen(characterId)
            }
            composable(Screen.Episodes.route) {
                EpisodesScreen(navController = navController)
            }
            composable(
                route = "episode_detail/{episodeId}",
                arguments = listOf(navArgument("episodeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("episodeId") ?: 0
                EpisodeDetailScreen(episodeId = id)
            }

            composable(Screen.Locations.route) {
                LocationsScreen(navController = navController)
            }
            composable(
                route = "location_detail/{locationId}",
                arguments = listOf(navArgument("locationId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("locationId") ?: 0
                LocationDetailScreen(locationId = id)
            }

        }
    }
}


fun currentScreenTitle(currentRoute: String?): String? {
    return when {
        currentRoute == Screen.Characters.route -> Screen.Characters.title
        currentRoute == Screen.Locations.route -> Screen.Locations.title
        currentRoute == Screen.Episodes.route -> Screen.Episodes.title
        currentRoute?.startsWith(Screen.CharacterDetail.route) == true -> "Детали персонажа"
        currentRoute?.startsWith(Screen.LocationDetail.route) == true -> "Детали локации"
        currentRoute?.startsWith(Screen.EpisodeDetail.route) == true -> "Детали эпизода"
        else -> ""
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    val items = listOf(
        BottomNavItem(Screen.Characters, Icons.Filled.Home),
        BottomNavItem(Screen.Locations, Icons.Filled.Place),
        BottomNavItem(Screen.Episodes, Icons.Filled.DateRange)
    )

    NavigationBar(
        containerColor = DarkGray
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.screen.title) },
                label = { item.screen.title?.let { Text(it) } },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.Black
                )
            )
        }
    }
}