package com.example.recipeapplication.presentation.screens.mealCategoryScreen

import androidx.compose.foundation.layout.Arrangement
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
import com.example.recipeapplication.presentation.components.MealsList
import com.example.recipeapplication.presentation.viewmodels.MealCategoryUiState
import com.example.recipeapplication.presentation.viewmodels.MealCategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealCategoryScreen(
    viewModel: MealCategoryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        is MealCategoryUiState.Error -> {}
        MealCategoryUiState.Loading -> {}
        is MealCategoryUiState.Success -> {
            val state = uiState as MealCategoryUiState.Success
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(state.items) { mealsCategory ->
                    MealsList(
                        mealsCategory = mealsCategory.category,
                        meals = mealsCategory.meals,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}