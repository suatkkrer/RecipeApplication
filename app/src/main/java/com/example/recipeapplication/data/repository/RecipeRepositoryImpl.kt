package com.example.recipeapplication.data.repository

import com.example.recipeapplication.data.remote.MealById.MealById
import com.example.recipeapplication.data.remote.MealByRegion.MealsByRegion
import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.data.remote.RecipeApi
import com.example.recipeapplication.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipeApi,
) : RecipeRepository {

    override suspend fun searchRecipesByLetter(query: String): MealsDTO {
        return api.searchByLetter(query)
    }

    override suspend fun getMealsByCategory(query: String): MealsDTO {
        return api.getMealsByCategory(query)
    }

    override suspend fun getMealCategory(): MealCategory {
        return api.getMealCategory()
    }

    override suspend fun getMealRegion(): MealsByRegion {
        return api.getMealsRegion()
    }

    override suspend fun getMealsByRegion(query: String): MealsDTO {
        return api.getMealsByRegion(query)
    }

    override suspend fun getMealsById(query: String): MealById {
        return api.getMealsById(query)
    }
}