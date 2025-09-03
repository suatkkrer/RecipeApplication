package com.example.recipeapplication.presentation.screens.homePage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.presentation.components.MealsCardUI
import com.example.recipeapplication.presentation.viewmodels.HomePageViewModel
import com.example.recipeapplication.presentation.viewmodels.SearchUiState
import com.example.recipeapplication.ui.theme.RecipeApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RecipeApplicationTheme {
                MyScreen()
            }
        }
    }
}


@Composable
fun MyScreen(
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

@Composable
fun MealsList(
    meals: List<Meal>,
    modifier: Modifier = Modifier,
    onMealClick: (Meal) -> Unit = {}
) {
    val listState = rememberLazyListState()
    val fling = rememberSnapFlingBehavior(lazyListState = listState)

    LazyRow(
        state = listState,
        flingBehavior = fling,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = meals,
            key = { it.idMeal ?: it.strMeal ?: it.hashCode().toString() }
        ) { meal ->
            MealsCardUI(
                meal = meal,
                // Give each card a concrete width for a nice carousel feel
                modifier = Modifier.width(280.dp),
                onClick = { onMealClick(meal) }
            )
        }
    }
}
