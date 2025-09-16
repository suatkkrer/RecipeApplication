package com.example.recipeapplication.data.repository

import com.example.recipeapplication.data.mapper.toDomain
import com.example.recipeapplication.data.remote.RecipeApi
import com.example.recipeapplication.domain.model.MealCategoryListDomain
import com.example.recipeapplication.domain.model.MealsByRegionDomain
import com.example.recipeapplication.domain.model.MealsDomain
import com.example.recipeapplication.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipeApi,
) : RecipeRepository {

    override suspend fun searchRecipesByLetter(query: String): MealsDomain {
        val dto = api.searchByLetter(query)
        return dto.toDomain()
    }

    override suspend fun getMealsByCategory(query: String): MealsDomain {
        val dto = api.getMealsByCategory(query)
        return dto.toDomain()
    }

    override suspend fun getMealCategory(): MealCategoryListDomain {
        val dto = api.getMealCategory()
        return dto.toDomain()
    }

    override suspend fun getMealRegion(): MealsByRegionDomain {
        val dto = api.getMealsRegion()
        return dto.toDomain()
    }

    override suspend fun getMealsByRegion(query: String): MealsDomain {
        val dto = api.getMealsByRegion(query)
        return dto.toDomain()
    }

    override suspend fun getMealsById(query: String): MealsDomain {
        val dto = api.getMealsById(query)
        return dto.toDomain()
    }
}