package com.example.icasei.data.remote.dto

data class SearchModelDto(
    val nextPageToken: String?,
    val prevPageToken: String?,
    val items: List<SearchItemDto>,
)
