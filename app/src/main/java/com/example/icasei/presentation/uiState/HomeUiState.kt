package com.example.icasei.presentation.uiState

data class HomeUiState(
    var searchText: String = "",
    val onSearchTextValueChanged: ((String) -> Unit)? = null,
)
