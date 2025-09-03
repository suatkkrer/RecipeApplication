package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO


interface RecipeRepository {
    suspend fun searchRecipesByLetter(query: String): MealsDTO
    suspend fun getMealsByCategory(query: String): MealCategory
    suspend fun getMealsByRegion(query: String): MealCategory
    suspend fun getMealsById(query: String): Meal
    suspend fun getMealCategory(): MealCategory
}