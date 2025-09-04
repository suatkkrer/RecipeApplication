package com.example.recipeapplication.domain.model

data class RegionMeals(
    val region: String,
    val meals: List<MealDomain>
)
