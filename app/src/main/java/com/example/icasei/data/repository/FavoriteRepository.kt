package com.example.icasei.data.repository

import com.example.icasei.data.local.IcaseiDatabase
import com.example.icasei.data.local.entity.FavoriteEntity
import com.example.icasei.domain.repository.IFavoriteRepository
import javax.inject.Inject

class FavoriteRepository @Inject
constructor(
    private val db: IcaseiDatabase,
) : IFavoriteRepository {

    override suspend fun getFavorites(): List<FavoriteEntity> = db.favoriteDao.getAll()

    override suspend fun checkFavorite(name: String): Boolean = db.favoriteDao.checkFavorite(name)

    override suspend fun upsert(favoriteEntity: FavoriteEntity) {
        db.favoriteDao.upsert(favoriteEntity)
    }

    override suspend fun deleteFavorite(name: String) {
        db.favoriteDao.deleteFavorite(name)
    }
}
