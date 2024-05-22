package com.nomaan.simplerecipes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomaan.simplerecipes.domain.models.Category
import com.nomaan.simplerecipes.domain.usecases.GetCategoriesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    sealed class HomeUiState {
        data class Idle(val categories: List<Category>) : HomeUiState()
        data object Loading : HomeUiState()
        data class Error(val errorMessage: String): HomeUiState()
    }

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle(emptyList()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _homeUiState.emit(HomeUiState.Loading)

            val categories = getCategoriesUseCase().getOrNull()

            if (categories?.isNotEmpty() == true) {
                delay(250)
                _homeUiState.emit(HomeUiState.Idle(categories))
            } else {
                _homeUiState.emit(HomeUiState.Error("Unable to fetch categories."))
            }
        }
    }
}