package com.example.recipeapplication.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.recipeapplication.presentation.screens.favoritesScreen.FavoritesScreen
import com.example.recipeapplication.presentation.screens.mealCategoryScreen.MealCategoryScreen
import com.example.recipeapplication.presentation.screens.mealRegionScreen.MealRegionScreen
import com.example.recipeapplication.presentation.screens.searchScreen.SearchScreen
import com.example.recipeapplication.presentation.screens.settingsScreen.SettingsScreen

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector = selectedIcon
)

object Routes {
    const val MEAL_CATEGORY_SCREEN = "mealCategoryScreen"
    const val MEAL_REGION_SCREEN = "mealRegionScreen"
    const val SEARCH_SCREEN = "searchScreen"
    const val FAVORITES_SCREEN = "favoritesScreen"
    const val SETTINGS_SCREEN = "settingScreen"
}

private val bottomItems = listOf(
    BottomNavItem(Routes.MEAL_CATEGORY_SCREEN, "Meal Category", Icons.Outlined.Home),
    BottomNavItem(Routes.MEAL_REGION_SCREEN, "Meal Region", Icons.Outlined.ShoppingCart),
    BottomNavItem(Routes.SEARCH_SCREEN, "Search", Icons.Outlined.Search),
    BottomNavItem(Routes.FAVORITES_SCREEN, "Favorites", Icons.Outlined.Favorite),
    BottomNavItem(Routes.SETTINGS_SCREEN, "Settings", Icons.Outlined.Person),
)

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomItems.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (currentDestination != null) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.selectedIcon, contentDescription = item.title) },
                        label = { Text(item.title, textAlign = TextAlign.Center) },
                        enabled = currentDestination != null
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MEAL_CATEGORY_SCREEN,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Routes.MEAL_CATEGORY_SCREEN) { MealCategoryScreen() }
        composable(Routes.MEAL_REGION_SCREEN) { MealRegionScreen() }
        composable(Routes.SEARCH_SCREEN) { SearchScreen() }
        composable(Routes.FAVORITES_SCREEN) { FavoritesScreen() }
        composable(Routes.SETTINGS_SCREEN) { SettingsScreen() }
    }
}
