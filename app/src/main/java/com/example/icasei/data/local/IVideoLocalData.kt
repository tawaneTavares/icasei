package com.example.icasei.data.local

import com.example.icasei.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface IVideoLocalData {

    fun getFavorites(): Flow<List<Favorite>>
    fun checkFavorite(videoId: String): Flow<Boolean>
    suspend fun upsert(favorite: Favorite)
    suspend fun deleteFavorite(videoId: String)
}
