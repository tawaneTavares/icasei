package com.example.icasei.domain.repository

import androidx.paging.PagingData
import com.example.icasei.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface IYoutubeRepository {

    fun getSearch(text: String): Flow<PagingData<SearchItem>>
}
