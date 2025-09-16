package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.domain.model.MealCategoryListDomain
import com.example.recipeapplication.domain.model.MealsByRegionDomain
import com.example.recipeapplication.domain.model.MealsDomain


interface RecipeRepository {
    suspend fun searchRecipesByLetter(query: String): MealsDomain
    suspend fun getMealsByCategory(query: String): MealsDomain
    suspend fun getMealsByRegion(query: String): MealsDomain
    suspend fun getMealsById(query: String): MealsDomain
    suspend fun getMealCategory(): MealCategoryListDomain
    suspend fun getMealRegion(): MealsByRegionDomain
}