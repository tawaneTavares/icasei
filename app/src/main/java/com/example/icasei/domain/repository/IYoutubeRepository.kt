package com.example.icasei.domain.repository

import com.example.icasei.data.remote.dto.SearchModel
import kotlinx.coroutines.flow.Flow

interface IYoutubeRepository {

    fun getSearch(text: String): Flow<SearchModel>
}
