package com.example.icasei.data.remote.dto

import com.example.icasei.domain.model.SearchItem

data class SearchItemDto(
    val id: SearchIdItem,
    val snippet: SnippetItem,
)

fun SearchItemDto.toDomain(): SearchItem {
    return SearchItem(
        id = id,
        snippet = snippet,
    )
}
