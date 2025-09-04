package com.example.recipeapplication.presentation.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapplication.domain.model.MealDomain

@Composable
fun MealsList(
    mealsCategory: String,
    meals: List<MealDomain>,
    modifier: Modifier = Modifier,
    onMealClick: (MealDomain) -> Unit = {}
) {
    val listState = rememberLazyListState()
    val fling = rememberSnapFlingBehavior(lazyListState = listState)

    Column() {
        Text(mealsCategory)
        Spacer(modifier = Modifier.height(30.dp))
        LazyRow(
            state = listState,
            flingBehavior = fling,
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = meals,
                key = { it.id }
            ) { meal ->
                MealsCardUI(
                    meal = meal,
                    modifier = Modifier.width(280.dp),
                    onClick = { onMealClick(meal) }
                )
            }
        }
    }

}