package com.example.recipeapplication.presentation.screens.favoritesScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FavoritesScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Text(
            text = "FavoritesScreen",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )
    }
}