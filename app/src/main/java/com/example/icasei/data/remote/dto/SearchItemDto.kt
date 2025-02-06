package com.example.icasei.data.remote.dto

import com.example.icasei.domain.model.VideoModel

data class SearchItemDto(
    val id: SearchIdItemDto,
    val snippet: SnippetItemDto,
)

fun SearchItemDto.toDomain(): VideoModel {
    return VideoModel(
        id = id.videoId,
        title = snippet.title,
        description = snippet.description,
        thumbnail = snippet.thumbnails.high.url,
    )
}
