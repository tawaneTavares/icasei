package com.example.icasei.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icasei.common.State
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.usecase.AddFavoriteUseCase
import com.example.icasei.domain.usecase.DeleteFavoriteUseCase
import com.example.icasei.domain.usecase.GetFavoriteUseCase
import com.example.icasei.presentation.uiState.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _favoritesList = MutableStateFlow<State<List<Favorite>?>>(State.idle())
    val favoritesList = _favoritesList.asStateFlow()

    private val _uiStateFavorites = MutableStateFlow(FavoritesUiState())
    val uiStateFavorites: StateFlow<FavoritesUiState>
        get() = _uiStateFavorites.asStateFlow()

    fun getFavoritesList() {
        viewModelScope.launch {
            getFavoriteUseCase.execute()
                .collect {
                    _favoritesList.emit(it)
                }
        }
    }

    fun updateFavorite(isFavorite: Boolean, favorite: Favorite) = viewModelScope.launch {
        if (isFavorite) {
            addFavoriteUseCase(favorite)
        } else {
            deleteFavoriteUseCase(favorite.title)
        }
    }
}
