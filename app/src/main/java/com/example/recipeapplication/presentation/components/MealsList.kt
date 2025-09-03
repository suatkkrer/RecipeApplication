package com.example.recipeapplication.presentation.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapplication.data.remote.MealsDTO.Meal

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
                modifier = Modifier.width(280.dp),
                onClick = { onMealClick(meal) }
            )
        }
    }
}