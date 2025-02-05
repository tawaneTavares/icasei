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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _uiStateFavorites = MutableStateFlow(FavoritesUiState())
    val uiStateFavorites: StateFlow<FavoritesUiState>
        get() = _uiStateFavorites.asStateFlow()

    init {
        getFavoritesList()
    }

    private fun getFavoritesList() {
        viewModelScope.launch {
            getFavoriteUseCase.execute()
                .collectLatest { state ->

                    when (state) {
                        is State.Data -> {
                            _uiStateFavorites.value = _uiStateFavorites.value.copy(
                                favoritesList = state.data,
                                loading = false,
                                showError = false,
                                msgError = null,
                            )
                        }

                        is State.Error -> {
                            _uiStateFavorites.value = _uiStateFavorites.value.copy(
                                loading = false,
                                showError = true,
                                msgError = state.cause?.message,
                            )
                        }

                        is State.Loading -> {
                            _uiStateFavorites.value = _uiStateFavorites.value.copy(
                                loading = true,
                                showError = false,
                                msgError = null,
                            )
                        }

                        else -> Unit
                    }
                }
        }
    }

    fun updateFavorite(isFavorite: Boolean, favorite: Favorite) = viewModelScope.launch {
        if (isFavorite) {
            addFavoriteUseCase(favorite)
        } else {
            deleteFavoriteUseCase(favorite.videoId)
            _uiStateFavorites.value = _uiStateFavorites.value.copy(
                favoritesList = _uiStateFavorites.value.favoritesList?.filter { it.videoId != favorite.videoId },
            )
        }
    }
}
