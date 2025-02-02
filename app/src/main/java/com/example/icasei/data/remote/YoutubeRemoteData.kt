package com.example.icasei.data.remote

import com.example.icasei.common.BaseRemoteData
import com.example.icasei.data.remote.api.IYoutubeApi
import com.example.icasei.data.remote.dto.SearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class YoutubeRemoteData(private val apiClient: IYoutubeApi) : BaseRemoteData(), IYoutubeRemoteData {

    override fun searchVideos(text: String): Flow<SearchModel> = flow {
        safeCall {
            val response = apiClient.getSearch("snippet", "video", text)

            threatResponse(response) {
                emit(it)
            }
        }
    }
}
