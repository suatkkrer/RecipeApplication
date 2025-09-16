package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.data.remote.MealById.MealById
import com.example.recipeapplication.data.remote.MealByRegion.MealsByRegion
import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO


interface RecipeRepository {
    suspend fun searchRecipesByLetter(query: String): MealsDTO
    suspend fun getMealsByCategory(query: String): MealsDTO
    suspend fun getMealsByRegion(query: String): MealsDTO
    suspend fun getMealsById(query: String): MealById
    suspend fun getMealCategory(): MealCategory
    suspend fun getMealRegion(): MealsByRegion
}