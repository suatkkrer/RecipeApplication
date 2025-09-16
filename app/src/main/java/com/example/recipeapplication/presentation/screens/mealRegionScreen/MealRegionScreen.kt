package com.example.recipeapplication.presentation.screens.mealRegionScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.recipeapplication.presentation.components.MealsList
import com.example.recipeapplication.presentation.viewmodels.MealRegionUiState
import com.example.recipeapplication.presentation.viewmodels.MealRegionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealRegionScreen(
    navController: NavHostController,
    viewModel: MealRegionViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is MealRegionUiState.Error -> {}
        MealRegionUiState.Loading -> {}
        is MealRegionUiState.Success -> {
            val state = uiState as MealRegionUiState.Success
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(state.items) { mealsCategory ->
                    MealsList(
                        mealsCategory = mealsCategory.region,
                        meals = mealsCategory.meals,
                        modifier = Modifier.fillMaxWidth(),
                        onMealClick = { mealId -> viewModel.onItemSelected(mealId = mealId) },
                        navController = navController
                    )
                }
            }
        }
    }
}