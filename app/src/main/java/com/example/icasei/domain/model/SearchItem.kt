package com.example.icasei.domain.model

import com.example.icasei.data.remote.dto.SearchIdItem
import com.example.icasei.data.remote.dto.SnippetItem

data class SearchItem(
    val id: SearchIdItem,
    val snippet: SnippetItem,
)
