package com.example.icasei.data.remote

import androidx.paging.PagingData
import com.example.icasei.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface IYoutubeRemoteData {

    fun searchVideos(text: String): Flow<PagingData<SearchItem>>
}
