package com.leehendryp.wtest.data.remote

import com.leehendryp.wtest.domain.Article

interface RemoteDataSource {
    suspend fun getArticlesAt(page: Int): List<Article>
}