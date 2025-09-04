package com.example.recipeapplication.domain.usecase

import com.example.recipeapplication.domain.mapper.toDomain
import com.example.recipeapplication.domain.model.CategoryMeals
import com.example.recipeapplication.domain.repository.RecipeRepository

class GetMealsByCategoryUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): List<CategoryMeals> {
        val categories = recipeRepository.getMealCategory()
        return categories.meals.map { c ->
            CategoryMeals(
                category = c.strCategory,
                meals = recipeRepository.getMealsByCategory(c.strCategory).meals.map {
                    it.toDomain()
                }
            )
        }
    }
}
