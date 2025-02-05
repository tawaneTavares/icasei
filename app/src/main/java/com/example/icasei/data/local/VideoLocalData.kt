package com.example.icasei.data.local

import com.example.icasei.data.local.entity.toFavorite
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.model.toFavoriteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoLocalData @Inject constructor(
    private val appDatabase: IcaseiDatabase,
) : IVideoLocalData {

    override fun getFavorites(): Flow<List<Favorite>> = flow {
        try {
            emit(appDatabase.favoriteDao.getAll().map { it.toFavorite() })
        } catch (e: Exception) {
            throw e
        }
    }

    override fun checkFavorite(videoId: String): Flow<Boolean> = flow {
        try {
            emit(appDatabase.favoriteDao.checkFavorite(videoId))
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun upsert(favorite: Favorite) {
        appDatabase.favoriteDao.upsert(favorite.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(videoId: String) {
        appDatabase.favoriteDao.deleteFavorite(videoId)
    }
}
