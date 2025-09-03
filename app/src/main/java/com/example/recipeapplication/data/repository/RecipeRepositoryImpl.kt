package com.example.recipeapplication.data.repository

import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.data.remote.RecipeApi
import com.example.recipeapplication.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipeApi,
) : RecipeRepository {

    override suspend fun searchRecipesByLetter(query: String): MealsDTO {
        return api.searchByLetter(query)
    }

    override suspend fun getMealsByCategory(query: String): MealCategory {
        return api.getMealsByCategory(query)
    }

    override suspend fun getMealCategory(): MealCategory {
        return api.getMealCategory()
    }

    override suspend fun getMealsByRegion(query: String): MealCategory {
        return api.getMealsByRegion(query)
    }

    override suspend fun getMealsById(query: String): Meal {
        return api.getMealsById(query)
    }
}