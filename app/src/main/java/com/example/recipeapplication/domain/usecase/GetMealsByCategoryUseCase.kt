package com.example.recipeapplication.domain.usecase

import com.example.recipeapplication.domain.model.CategoryMeals
import com.example.recipeapplication.domain.repository.RecipeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetMealsByCategoryUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): List<CategoryMeals> = coroutineScope {
        val categories = recipeRepository.getMealCategory()
        categories.mealCategoryList.map { c ->
            async {
                val meals =
                    recipeRepository.getMealsByCategory(c.categoryName).meals.map { it }
                CategoryMeals(category = c.categoryName, meals = meals)
            }
        }.awaitAll()
    }
}
