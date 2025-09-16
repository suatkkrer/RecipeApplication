package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.data.remote.MealById.MealById
import com.example.recipeapplication.data.remote.MealByRegion.MealsByRegion
import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.domain.model.MealsDomain


interface RecipeRepository {
    suspend fun searchRecipesByLetter(query: String): MealsDomain
    suspend fun getMealsByCategory(query: String): MealsDomain
    suspend fun getMealsByRegion(query: String): MealsDomain
    suspend fun getMealsById(query: String): MealById
    suspend fun getMealCategory(): MealCategory
    suspend fun getMealRegion(): MealsByRegion
}