package com.example.recipeapplication.domain.repository

import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.data.remote.RecipeApi


interface RecipeRepository {
    suspend fun searchRecipes(query: String): MealsDTO
}

class RecipeRepositoryImpl(
    private val api: RecipeApi,
) : RecipeRepository {

    override suspend fun searchRecipes(query: String): MealsDTO {
        return api.search("a")
    }
}