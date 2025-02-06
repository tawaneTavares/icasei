package com.example.icasei.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoModel(
    val id: String,
    val title: String,
    val thumbnail: String,
)
