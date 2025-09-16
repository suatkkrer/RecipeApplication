package com.example.recipeapplication.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SelectItemUseCase {
    private val _selectedItemId = MutableStateFlow<String?>(null)
    val selectedItemId: StateFlow<String?> = _selectedItemId

    fun selectItem(id: String) {
        _selectedItemId.value = id
    }
}