package com.leehendryp.wtest.domain

import com.leehendryp.wtest.data.entities.ArticleResponse
import com.leehendryp.wtest.data.entities.PageResponse

fun PageResponse.toPage() = Page(
    count = count ?: 0,
    articleList = articleResponseList?.toArticleList() ?: listOf()
)

fun List<ArticleResponse>.toArticleList(): List<Article> {
    val list = mutableListOf<Article>()
    forEach { response -> list.add(response.toArticle()) }
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
