package com.example.icasei.data.remote

import com.example.icasei.data.remote.dto.SearchModel
import kotlinx.coroutines.flow.Flow

interface IYoutubeRemoteData {

    fun searchVideos(text: String): Flow<SearchModel>
}
