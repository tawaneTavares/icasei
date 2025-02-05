package com.example.icasei.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SnippetItem(
    val publishedAt: String,
    val title: String,
    val description: String,
    val thumbnails: ThumbnailItem,
    val channelTitle: String,
)
