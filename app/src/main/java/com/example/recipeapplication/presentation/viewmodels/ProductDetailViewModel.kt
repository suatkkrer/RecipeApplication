package com.example.recipeapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.data.remote.MealById.MealById
import com.example.recipeapplication.domain.repository.RecipeRepository
import com.example.recipeapplication.domain.usecase.SelectItemUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val selectItemUseCase: SelectItemUseCase,
    private val repository: RecipeRepository
) : ViewModel() {

    private val _meal = MutableStateFlow<MealById?>(null)
    val meal: StateFlow<MealById?> = _meal

    init {
        viewModelScope.launch {
            selectItemUseCase.selectedItemId.collect { id ->
                if (!id.isNullOrEmpty()) {
                    val details = repository.getMealsById(id)
                    _meal.value = details
                }
            }
        }
    }
}