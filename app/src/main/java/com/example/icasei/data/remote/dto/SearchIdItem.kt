package com.example.icasei.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchIdItem(
    val videoId: String,
)
