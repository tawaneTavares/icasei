package com.example.icasei.domain.repository

import com.example.icasei.data.remote.IYoutubeRemoteData
import com.example.icasei.data.remote.dto.SearchModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class YoutubeRepository @Inject constructor(
    private val remoteData: IYoutubeRemoteData,
) : IYoutubeRepository {

    override fun getSearch(text: String): Flow<SearchModel> = remoteData.searchVideos(text).flowOn(Dispatchers.IO)
}
