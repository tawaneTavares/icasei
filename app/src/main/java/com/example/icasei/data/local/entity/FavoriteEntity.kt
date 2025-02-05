package com.example.icasei.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.icasei.domain.model.Favorite

@Entity
data class FavoriteEntity(
    @PrimaryKey
    val videoId: String,
    val title: String,
    val thumbnailUrl: String,
)

fun FavoriteEntity.toFavorite(): Favorite = Favorite(
    title = title,
    videoId = videoId,
    thumbnailUrl = thumbnailUrl,
)
