package com.example.icasei.domain.model

import com.example.icasei.data.local.entity.FavoriteEntity

data class Favorite(
    val title: String,
    val videoId: String,
    val thumbnailUrl: String,
)

fun Favorite.toFavoriteEntity(): FavoriteEntity = FavoriteEntity(
    title = title,
    videoId = videoId,
    thumbnailUrl = thumbnailUrl,
)
