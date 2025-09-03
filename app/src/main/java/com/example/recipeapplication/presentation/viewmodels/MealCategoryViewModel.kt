package com.example.recipeapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.data.remote.MealsDTO.Meal
import com.example.recipeapplication.domain.usecase.GetMealsByCategoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MealCategoryViewModel(val getMealsByCategoryUseCase: GetMealsByCategoryUseCase): ViewModel() {

    val uiState: StateFlow<MealCategoryUiState> =
        flow {
            emit(MealCategoryUiState.Loading)
            try {
                val data = getMealsByCategoryUseCase.invoke()
                emit(MealCategoryUiState.Success(data))
            } catch (t: Throwable) {
                emit(MealCategoryUiState.Error(t))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MealCategoryUiState.Idle)
}

sealed interface MealCategoryUiState {
    data object Idle : MealCategoryUiState
    data object Loading : MealCategoryUiState
    data class Success(val items: List<List<Meal>>) : MealCategoryUiState
    data class Error(val throwable: Throwable) : MealCategoryUiState
}
