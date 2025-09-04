package com.example.recipeapplication.domain.usecase

import com.example.recipeapplication.domain.mapper.toDomain
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
        categories.meals.map { c ->
            async {
                val meals = recipeRepository.getMealsByRegion(c.strArea).meals.map { it.toDomain() }
                RegionMeals(region = c.strArea, meals = meals)
            }
        }.awaitAll()
    }
}
