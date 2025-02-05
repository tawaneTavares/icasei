package com.example.icasei.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.icasei.domain.usecase.GetSearchUseCase
import com.example.icasei.presentation.uiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: String
        get() = _query.value

    val searchList = _query
        .debounce(1000)
        .flatMapLatest { query ->
            getSearchUseCase.invoke(query)
        }.cachedIn(viewModelScope)

    private val _uiStateHome = MutableStateFlow(HomeUiState())
    val uiStateHome: StateFlow<HomeUiState>
        get() = _uiStateHome.asStateFlow()

    init {
        observeHomeFields()
    }

    fun updateQuery(q: String) = _query.update { q }

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
