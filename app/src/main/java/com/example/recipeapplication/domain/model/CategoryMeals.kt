package com.example.recipeapplication.domain.model

data class CategoryMeals(
    val category: String,
    val meals: List<MealDomain>
)
