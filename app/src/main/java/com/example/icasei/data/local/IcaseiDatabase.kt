package com.example.icasei.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.icasei.data.local.dao.FavoriteDao
import com.example.icasei.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class IcaseiDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}
