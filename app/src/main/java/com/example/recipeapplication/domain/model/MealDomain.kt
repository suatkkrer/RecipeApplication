package com.example.recipeapplication.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MealDomain(
    val id: String,
    val name: String,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val thumbnail: String?,
    val tags: List<String>,
    val youtubeUrl: String?,
    val sourceUrl: String?,
    val ingredients: List<Ingredient>
)

@Serializable
data class Ingredient(
    val name: String,
    val measure: String
)

