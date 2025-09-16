package com.example.recipeapplication.data.mapper

import com.example.recipeapplication.data.remote.MealByRegion.MealRegion
import com.example.recipeapplication.data.remote.MealByRegion.MealsByRegion
import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealCategory.MealCategoryList
import com.example.recipeapplication.data.remote.MealsDTO.MealDTO
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.domain.model.Ingredient
import com.example.recipeapplication.domain.model.MealCategoryDomain
import com.example.recipeapplication.domain.model.MealCategoryListDomain
import com.example.recipeapplication.domain.model.MealDomain
import com.example.recipeapplication.domain.model.MealRegionDomain
import com.example.recipeapplication.domain.model.MealsByRegionDomain
import com.example.recipeapplication.domain.model.MealsDomain

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

fun MealsDTO.toDomain(): MealsDomain {
    return MealsDomain(
        meals = meals.map { it.toDomain() }
    )
}

fun MealCategory.toDomain(): MealCategoryDomain {
    return MealCategoryDomain(
        categoryName = strCategory
    )
}

fun MealCategoryList.toDomain(): MealCategoryListDomain {
    return MealCategoryListDomain(
        mealCategoryList = meals.map { it.toDomain() }
    )
}

fun MealRegion.toDomain(): MealRegionDomain {
    return MealRegionDomain(
        region = strArea
    )
}

fun MealsByRegion.toDomain(): MealsByRegionDomain {
    return MealsByRegionDomain(
        mealsByRegion = meals.map { it.toDomain() }
    )
}