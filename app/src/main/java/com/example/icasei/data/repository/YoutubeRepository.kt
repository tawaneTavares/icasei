package com.example.icasei.data.repository

import androidx.paging.PagingData
import com.example.icasei.data.remote.IYoutubeRemoteData
import com.example.icasei.domain.model.SearchItem
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class YoutubeRepository @Inject constructor(
    private val remoteData: IYoutubeRemoteData,
) : IYoutubeRepository {

    override fun getSearch(text: String): Flow<PagingData<SearchItem>> = remoteData.searchVideos(text).flowOn(Dispatchers.IO)
}
