package com.example.icasei.data.repository

import androidx.paging.PagingData
import com.example.icasei.data.local.IVideoLocalData
import com.example.icasei.data.remote.IYoutubeRemoteData
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.model.VideoModel
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class YoutubeRepository @Inject constructor(
    private val remoteData: IYoutubeRemoteData,
    private val localData: IVideoLocalData,
) : IYoutubeRepository {

    override fun getSearch(text: String): Flow<PagingData<VideoModel>> = remoteData.searchVideos(text).flowOn(Dispatchers.IO)

    override fun getFavorites(): Flow<List<Favorite>> = localData.getFavorites()
        .flowOn(Dispatchers.IO)

    override fun checkFavorite(videoId: String): Flow<Boolean> = localData.checkFavorite(videoId)
        .flowOn(Dispatchers.IO)

    override suspend fun addFavorite(favorite: Favorite) = localData.upsert(favorite)

    override suspend fun deleteFavorite(videoId: String) = localData.deleteFavorite(videoId)
}
