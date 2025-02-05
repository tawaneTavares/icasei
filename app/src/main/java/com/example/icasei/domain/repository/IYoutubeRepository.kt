package com.example.icasei.domain.repository

import androidx.paging.PagingData
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface IYoutubeRepository {

    fun getSearch(text: String): Flow<PagingData<SearchItem>>
    fun getFavorites(): Flow<List<Favorite>>
    fun checkFavorite(videoId: String): Flow<Boolean>
    suspend fun addFavorite(favorite: Favorite)
    suspend fun deleteFavorite(videoId: String)
}
