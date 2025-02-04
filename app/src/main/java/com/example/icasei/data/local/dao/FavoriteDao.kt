package com.example.icasei.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.icasei.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Upsert
    suspend fun upsert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favoriteEntity")
    fun getAll(): List<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT * FROM favoriteEntity WHERE videoId = :videoId)")
    fun checkFavorite(videoId: String): Boolean

    @Query("DELETE FROM favoriteEntity WHERE videoId = :videoId")
    suspend fun deleteFavorite(videoId: String)
}
