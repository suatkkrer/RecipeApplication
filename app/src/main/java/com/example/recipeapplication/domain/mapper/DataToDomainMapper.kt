package com.example.recipeapplication.domain.mapper

import com.example.recipeapplication.data.remote.MealsDTO.MealDTO
import com.example.recipeapplication.domain.model.Ingredient
import com.example.recipeapplication.domain.model.MealDomain

fun MealDTO.toDomain(): MealDomain {
    val ingredients = (1..20).mapNotNull { index ->
        val ingredient = this::class.members
            .firstOrNull { it.name == "strIngredient$index" }
            ?.call(this) as? String
        val measure = this::class.members
            .firstOrNull { it.name == "strMeasure$index" }
            ?.call(this) as? String

        if (!ingredient.isNullOrBlank()) {
            Ingredient(
                name = ingredient.trim(),
                measure = measure?.trim().orEmpty()
            )
        } else null
    }

    return MealDomain(
        id = idMeal.orEmpty(),
        name = strMeal.orEmpty(),
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        thumbnail = strMealThumb,
        tags = strTags?.split(",")?.map { it.trim() } ?: emptyList(),
        youtubeUrl = strYoutube,
        sourceUrl = strSource,
        ingredients = ingredients
    )
}