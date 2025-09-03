package com.example.recipeapplication.domain.usecase

import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.domain.repository.RecipeRepository

class GetMealsByCategoryUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): List<List<Meal>> {
        val categories = recipeRepository.getMealCategory()
        return categories.meals.map { c ->
            recipeRepository.getMealsByCategory(c.strCategory).meals
        }
    }
}
