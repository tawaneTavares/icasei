package com.example.icasei.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.icasei.data.remote.api.IYoutubeApi
import com.example.icasei.data.remote.dto.SearchItemDto
import retrofit2.HttpException

class YoutubeSearchPagingSource(
    private val apiClient: IYoutubeApi,
    private val text: String,
) : PagingSource<String, SearchItemDto>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, SearchItemDto> {
        val pageToken = params.key ?: ""
        return try {
            val response = apiClient.getSearch(
                part = "snippet",
                type = "video",
                maxResults = params.loadSize,
                pageToken = pageToken,
                text = text,
            )

            if (response.isSuccessful) {
                val searchModel = response.body()
                val items = searchModel?.items ?: emptyList()
                val nextKey = searchModel?.nextPageToken
                val prevKey = searchModel?.prevPageToken

                LoadResult.Page(
                    data = items,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            throw e
        } catch (httpException: HttpException) {
            throw httpException
        }
    }

    override fun getRefreshKey(state: PagingState<String, SearchItemDto>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }
}
