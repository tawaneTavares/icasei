package com.example.icasei.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icasei.common.State
import com.example.icasei.data.remote.dto.SearchModel
import com.example.icasei.domain.usecase.GetSearchUseCase
import com.example.icasei.presentation.uiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
) : ViewModel() {

    private val _searchList = MutableStateFlow<State<SearchModel?>>(State.idle())
    val searchList = _searchList.asStateFlow()

    private val _uiStateHome = MutableStateFlow(HomeUiState())
    val uiStateHome: StateFlow<HomeUiState>
        get() = _uiStateHome.asStateFlow()

    init {
        observeHomeFields()
    }

    fun getSearch(text: String) {
        viewModelScope.launch {
            getSearchUseCase.execute(text)
                .collect {
                    _searchList.emit(it)
                }
        }
    }

    private fun observeHomeFields() {
        _uiStateHome.update { state ->
            state.copy(
                onSearchTextValueChanged = {
                    _uiStateHome.value = _uiStateHome.value.copy(
                        searchText = it,
                    )
                },
            )
        }
    }
}
