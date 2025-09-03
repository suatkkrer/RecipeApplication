package com.example.recipeapplication.presentation.screens.mealCategoryScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapplication.presentation.components.MealsList
import com.example.recipeapplication.presentation.viewmodels.HomePageViewModel
import com.example.recipeapplication.presentation.viewmodels.SearchUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealCategoryScreen(
    viewModel: HomePageViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        is SearchUiState.Error -> {}
        SearchUiState.Idle -> {}
        SearchUiState.Loading -> {}
        is SearchUiState.Success -> {
            val state = uiState as SearchUiState.Success
            MealsList(meals = state.items.meals,
                modifier = Modifier.padding(top = 100.dp))
        }
    }
}