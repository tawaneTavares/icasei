package com.example.icasei.data.remote.api

import com.example.icasei.data.remote.dto.SearchModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IYoutubeApi {

    @GET("search")
    suspend fun getSearch(
        @Query("part") part: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String,
        @Query("q") text: String,
    ): Response<SearchModelDto>
}
