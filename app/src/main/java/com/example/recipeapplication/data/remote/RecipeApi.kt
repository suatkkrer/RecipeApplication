package com.example.recipeapplication.data.remote

import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query


    interface RecipeApi {
        @GET("api/json/v1/1/search.php")
        suspend fun search(
            @Query("f") query: String,
        ): MealsDTO
    }