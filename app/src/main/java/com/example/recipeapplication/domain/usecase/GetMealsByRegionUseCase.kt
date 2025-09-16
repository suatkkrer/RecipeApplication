package com.example.recipeapplication.domain.usecase

import com.example.recipeapplication.domain.model.RegionMeals
import com.example.recipeapplication.domain.repository.RecipeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetMealsByRegionUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): List<RegionMeals> = coroutineScope {
        val categories = recipeRepository.getMealRegion()
        categories.mealsByRegion.map { mealByRegion ->
            async {
                val meals = recipeRepository.getMealsByRegion(mealByRegion.region).meals.map { it }
                RegionMeals(region = mealByRegion.region, meals = meals)
            }
        }.awaitAll()
    }
}
