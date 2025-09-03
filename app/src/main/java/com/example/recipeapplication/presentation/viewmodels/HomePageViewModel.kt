package com.example.recipeapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.data.remote.MealsDTO.MealsDTO
import com.example.recipeapplication.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomePageViewModel(val recipeRepository: RecipeRepository): ViewModel() {

    private val query = "a"

    val uiState: StateFlow<SearchUiState> =
        flow {
            emit(SearchUiState.Loading)
            try {
                val data = recipeRepository.searchRecipesByLetter(query)
                emit(SearchUiState.Success(data))
            } catch (t: Throwable) {
                emit(SearchUiState.Error(t))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SearchUiState.Idle)
}

sealed interface SearchUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data class Success(val items: MealsDTO) : SearchUiState
    data class Error(val throwable: Throwable) : SearchUiState
}
