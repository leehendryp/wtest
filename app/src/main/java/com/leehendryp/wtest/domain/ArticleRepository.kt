package com.leehendryp.wtest.domain

interface ArticleRepository {
    suspend fun getArticlesAt(page: Int): List<Article>
}