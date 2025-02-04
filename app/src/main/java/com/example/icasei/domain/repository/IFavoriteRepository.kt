package com.example.icasei.domain.repository

import com.example.icasei.data.local.entity.FavoriteEntity

interface IFavoriteRepository {
    suspend fun getFavorites(): List<FavoriteEntity>
    suspend fun checkFavorite(name: String): Boolean
    suspend fun upsert(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(name: String)
}
