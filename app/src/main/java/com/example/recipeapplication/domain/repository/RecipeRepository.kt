package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.data.remote.RecipeApi


interface RecipeRepository {
    suspend fun searchRecipesByLetter(query: String): MealsDTO
    suspend fun getMealsByCategory(query: String): MealCategory
    suspend fun getMealsByRegion(query: String): MealCategory
    suspend fun getMealsById(query: String): Meal
    suspend fun getMealCategory(): MealCategory
}

class RecipeRepositoryImpl(
    private val api: RecipeApi,
) : RecipeRepository {

    override suspend fun searchRecipesByLetter(query: String): MealsDTO {
        return api.searchByLetter("a")
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