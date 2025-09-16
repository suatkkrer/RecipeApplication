package com.example.recipeapplication.presentation.screens.productDetailScreen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.recipeapplication.data.remote.MealById.Meal
import com.example.recipeapplication.presentation.viewmodels.ProductDetailViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = koinViewModel(),
    navController: NavHostController,
) {
    val meal by viewModel.meal.collectAsState()

    val mealDetail = meal?.meals?.firstOrNull()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(mealDetail?.strMeal ?: "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
    ) { padding ->
        mealDetail?.let { detail ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                item {
                    AsyncImage(
                        model = detail.strMealThumb,
                        contentDescription = detail.strMeal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = detail.strMeal ?: "",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${detail.strCategory} • ${detail.strArea}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }

                detail.strYoutube
                    ?.takeIf { it.isNotBlank() }
                    ?.let { youtube ->
                        item {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Red,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    ) {
                                        append("Youtube Link: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colorScheme.onBackground,
                                            fontWeight = FontWeight.Normal
                                        )
                                    ) {
                                        append(youtube)
                                    }
                                },
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                    .clickable {
                                        val intent = Intent(Intent.ACTION_VIEW, youtube.toUri())
                                        navController.context.startActivity(intent)
                                    }
                            )
                        }
                    }

                item {
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                val ingredients = getIngredients(detail)
                items(ingredients) { (ingredient, measure) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = ingredient, style = MaterialTheme.typography.bodyMedium)
                        Text(text = measure, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                item {
                    Text(
                        text = "Instructions",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                item {
                    Text(
                        text = detail.strInstructions ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

fun getIngredients(meal: Meal): List<Pair<String, String>> {
    val ingredients = mutableListOf<Pair<String, String>>()
    val fields = listOf(
        meal.strIngredient1 to meal.strMeasure1,
        meal.strIngredient2 to meal.strMeasure2,
        meal.strIngredient3 to meal.strMeasure3,
        meal.strIngredient4 to meal.strMeasure4,
        meal.strIngredient5 to meal.strMeasure5,
        meal.strIngredient6 to meal.strMeasure6,
        meal.strIngredient7 to meal.strMeasure7,
        meal.strIngredient8 to meal.strMeasure8,
        meal.strIngredient9 to meal.strMeasure9,
        meal.strIngredient10 to meal.strMeasure10,
        meal.strIngredient11 to meal.strMeasure11,
        meal.strIngredient12 to meal.strMeasure12,
        meal.strIngredient13 to meal.strMeasure13,
        meal.strIngredient14 to meal.strMeasure14,
        meal.strIngredient15 to meal.strMeasure15,
        meal.strIngredient16 to meal.strMeasure16,
        meal.strIngredient17 to meal.strMeasure17,
        meal.strIngredient18 to meal.strMeasure18,
        meal.strIngredient19 to meal.strMeasure19,
        meal.strIngredient20 to meal.strMeasure20,
    )

    fields.forEach { (ingredient, measure) ->
        if (!ingredient.isNullOrBlank() && ingredient.isNotEmpty()) {
            ingredients.add(ingredient to (measure ?: ""))
        }
    }
    return ingredients
}