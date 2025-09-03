package com.example.recipeapplication.data.remote

import com.example.recipeapplication.data.remote.MealCategory.MealCategory
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/json/v1/1/search.php")
        suspend fun searchByLetter(
            @Query("f") query: String,
        ): MealsDTO

    @GET("api/json/v1/1/list.php?c=list")
    suspend fun getMealCategory(): MealCategory

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByCategory(
        @Query("c") query: String,
    ): MealsDTO

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByRegion(
        @Query("a") query: String, ): MealCategory

    @GET("api/json/v1/1/lookup.php?i=52772")
    suspend fun getMealsById(
        @Query("i") query: String, ): Meal
}