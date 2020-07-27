package com.leehendryp.wtest.data

import com.leehendryp.wtest.data.entities.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeatThreeAPI {
    companion object {
        const val BASE_URL = "https://5bb1cd166418d70014071c8e.mockapi.io/"
        private const val ENDPOINT = "mobile/1-1/articles"
        private const val MAX_ENTRIES = 10
    }

    @GET(ENDPOINT)
    suspend fun getArticlesAt(
        @Query("page") page: Int,
        @Query("limit") limit: Int = MAX_ENTRIES
    ): Response<PageResponse>
}
