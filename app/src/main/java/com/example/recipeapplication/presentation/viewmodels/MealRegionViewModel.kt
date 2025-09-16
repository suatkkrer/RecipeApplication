package com.example.recipeapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.domain.model.RegionMeals
import com.example.recipeapplication.domain.usecase.GetMealsByRegionUseCase
import com.example.recipeapplication.domain.usecase.SelectItemUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MealRegionViewModel(
    private val getMealsByRegionUseCase: GetMealsByRegionUseCase,
    val selectItemUseCase: SelectItemUseCase
) : ViewModel() {

    val uiState: StateFlow<MealRegionUiState> =
        flow {
            emit(MealRegionUiState.Loading)
            try {
                val data = getMealsByRegionUseCase.invoke()
                emit(MealRegionUiState.Success(data))
            } catch (t: Throwable) {
                emit(MealRegionUiState.Error(t))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MealRegionUiState.Loading)


    fun onItemSelected(mealId: String) {
        selectItemUseCase.selectItem(mealId)
    }
}

sealed interface MealRegionUiState {
    data object Loading : MealRegionUiState
    data class Success(val items: List<RegionMeals>) : MealRegionUiState
    data class Error(val throwable: Throwable) : MealRegionUiState
}