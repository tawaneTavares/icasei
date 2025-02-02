package com.example.icasei.presentation.uiState

import com.example.icasei.data.remote.dto.SearchModel

data class HomeUiState(
    var searchData: SearchModel? = null,
    var searchText: String = "",
    val onSearchTextValueChanged: ((String) -> Unit)? = null,
)
