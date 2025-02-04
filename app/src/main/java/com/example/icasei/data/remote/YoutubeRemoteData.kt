package com.example.icasei.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.icasei.common.BaseRemoteData
import com.example.icasei.data.remote.api.IYoutubeApi
import com.example.icasei.data.remote.dto.SearchItemDto
import com.example.icasei.data.remote.dto.toDomain
import com.example.icasei.data.remote.pagingSource.YoutubeSearchPagingSource
import com.example.icasei.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class YoutubeRemoteData(private val apiClient: IYoutubeApi) : BaseRemoteData(), IYoutubeRemoteData {

    override fun searchVideos(text: String): Flow<PagingData<SearchItem>> = Pager(
        config = PagingConfig(
            pageSize = 15,
            enablePlaceholders = false,
            initialLoadSize = 15,
        ),
        pagingSourceFactory = {
            YoutubeSearchPagingSource(apiClient, text)
        },
    ).flow
        .map { value: PagingData<SearchItemDto> ->
            value.map { searchITem: SearchItemDto ->
                searchITem.toDomain()
            }
        }
}
