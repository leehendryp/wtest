package com.leehendryp.wtest.domain

import com.leehendryp.wtest.data.entities.ArticleResponse
import com.leehendryp.wtest.data.entities.PageResponse


fun PageResponse.retrieveArticles(): List<Article> {
    val list = mutableListOf<Article>()
    articleResponseList?.map { response -> list.add(response.toArticle()) }
    return list
}

fun ArticleResponse.toArticle() = Article(
    author = author ?: "",
    body = body ?: "",
    hero = hero ?: "",
    id = id ?: "",
    limit = limit ?: "",
    page = page ?: "",
    publishedAt = publishedAt ?: "",
    summary = summary ?: "",
    title = title ?: ""
)
