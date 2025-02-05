package com.example.icasei.presentation.uiState

import com.example.icasei.domain.model.Favorite

data class FavoritesUiState(
    var favoritesList: List<Favorite>? = emptyList(),
)
