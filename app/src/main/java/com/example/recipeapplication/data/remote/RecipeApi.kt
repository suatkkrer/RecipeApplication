package com.example.recipeapplication.data.remote

import com.example.recipeapplication.data.remote.MealByRegion.MealsByRegion
import com.example.recipeapplication.data.remote.MealCategory.MealCategoryList
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/json/v1/1/search.php")
        suspend fun searchByLetter(
            @Query("f") query: String,
        ): MealsDTO

    @GET("api/json/v1/1/list.php?c=list")
    suspend fun getMealCategory(): MealCategoryList

    @GET("api/json/v1/1/list.php?a=list")
    suspend fun getMealsRegion(): MealsByRegion

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByCategory(
        @Query("c") query: String
    ): MealsDTO

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByRegion(
        @Query("a") query: String
    ): MealsDTO

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealsById(
        @Query("i") id: String
    ): MealsDTO
}