package com.example.icasei.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icasei.common.State
import com.example.icasei.data.remote.dto.SearchModel
import com.example.icasei.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
) : ViewModel() {

    private val _searchList = MutableStateFlow<State<SearchModel?>>(State.idle())
    val searchList = _searchList.asStateFlow()

    fun getSearch(text: String) {
        viewModelScope.launch {
            getSearchUseCase.execute(text)
                .collect {
                    _searchList.emit(it)
                }
        }
    }
}
